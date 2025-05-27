// Event Class Definition
class Event {
    constructor({
        id,
        name,
        date,
        seats = 0,
        category,
        description = '',
        location = 'TBD',
        presenter = 'TBD'
    }) {
        this.id = id;
        this.name = name;
        this.date = new Date(date);
        this.seats = seats;
        this.category = category;
        this.description = description;
        this.location = location;
        this.presenter = presenter;
        this.registeredUsers = new Set();
        this.waitlist = [];
    }

    // Instance methods
    checkAvailability() {
        const { seats, waitlist, date, registeredUsers } = this;
        return {
            available: seats > 0,
            remainingSeats: seats,
            waitlistLength: waitlist.length,
            isUpcoming: date >= new Date(),
            registeredCount: registeredUsers.size
        };
    }

    registerUser(userId) {
        const { registeredUsers, seats, waitlist } = this;
        
        if (registeredUsers.has(userId)) {
            throw new Error('User already registered for this event');
        }

        if (seats > 0) {
            this.seats--;
            registeredUsers.add(userId);
            return { success: true, status: 'registered' };
        } else {
            waitlist.push(userId);
            return { success: true, status: 'waitlisted' };
        }
    }

    cancelRegistration(userId) {
        const { registeredUsers, waitlist } = this;
        
        if (registeredUsers.has(userId)) {
            registeredUsers.delete(userId);
            this.seats++;
            
            // Handle waitlist
            if (waitlist.length > 0) {
                const nextUser = waitlist.shift();
                this.seats--;
                registeredUsers.add(nextUser);
                return { success: true, status: 'cancelled', promoted: nextUser };
            }
            return { success: true, status: 'cancelled' };
        }
        throw new Error('User not registered for this event');
    }

    // Static methods
    static fromJSON(data) {
        return new Event(data);
    }

    toJSON() {
        const {
            id,
            name,
            date,
            seats,
            category,
            description,
            location,
            presenter,
            registeredUsers,
            waitlist
        } = this;

        return {
            id,
            name,
            date: date.toISOString().split('T')[0],
            seats,
            category,
            description,
            location,
            presenter,
            registeredCount: registeredUsers.size,
            waitlistCount: waitlist.length
        };
    }
}

// Mock API Service
const EventAPI = {
    // Simulate API delay
    delay: (ms) => new Promise(resolve => setTimeout(resolve, ms)),

    // Mock event data
    mockEvents: [
        {
            id: 1,
            name: "Community Meetup 2024",
            date: "2024-03-15",
            seats: 50,
            category: "Community",
            description: "Annual community gathering",
            location: "Community Center"
        },
        // ... existing events ...
    ],

    // Fetch all events (Promise-based)
    getAllEvents: function() {
        return new Promise((resolve, reject) => {
            this.delay(1000) // Simulate network delay
                .then(() => {
                    if (Math.random() > 0.1) { // 90% success rate
                        resolve(this.mockEvents);
                    } else {
                        reject(new Error('Failed to fetch events'));
                    }
                });
        });
    },

    // Get event by ID (Promise-based)
    getEventById: function(id) {
        return new Promise((resolve, reject) => {
            this.delay(500)
                .then(() => {
                    const event = this.mockEvents.find(e => e.id === id);
                    if (event) {
                        resolve(event);
                    } else {
                        reject(new Error('Event not found'));
                    }
                });
        });
    },

    // Register for event (async/await)
    async registerForEvent(eventId, userId) {
        await this.delay(800); // Simulate network delay
        const event = this.mockEvents.find(e => e.id === eventId);
        
        if (!event) {
            throw new Error('Event not found');
        }

        if (event.seats > 0) {
            event.seats--;
            return { success: true, message: 'Registration successful' };
        } else {
            throw new Error('No seats available');
        }
    },

    // Mock API endpoint for user registration
    registerUser: async function(userData) {
        await this.delay(1500); // Simulate network latency
        
        // Simulate server validation
        if (!userData.name || !userData.email) {
            throw new Error('Invalid user data');
        }

        // Simulate 10% failure rate
        if (Math.random() > 0.9) {
            throw new Error('Server error: Please try again');
        }

        return {
            success: true,
            data: {
                userId: `user-${Date.now()}`,
                registrationDate: new Date().toISOString(),
                ...userData
            },
            message: 'Registration successful'
        };
    }
};

// Event Management Module using IIFE for encapsulation
const EventManager = (function() {
    // Private events array
    let events = [];

    // Load events using Promise chain
    function loadEventsPromise() {
        UIManager.showLoading();
        
        return EventAPI.getAllEvents()
            .then(fetchedEvents => {
                events = fetchedEvents.map(event => new Event(event));
                return events;
            })
            .catch(error => {
                console.error('Error loading events:', error);
                throw error;
            })
            .finally(() => {
                UIManager.hideLoading();
            });
    }

    // Load events using async/await
    async function loadEventsAsync() {
        try {
            UIManager.showLoading();
            const fetchedEvents = await EventAPI.getAllEvents();
            events = fetchedEvents.map(event => new Event(event));
            return events;
        } catch (error) {
            console.error('Error loading events:', error);
            throw error;
        } finally {
            UIManager.hideLoading();
        }
    }

    // Register user with async/await
    async function registerUserAsync(eventId, userData) {
        try {
            UIManager.showLoading();
            await EventAPI.registerForEvent(eventId, userData.id);
            const event = events.find(e => e.id === eventId);
            
            if (!event) {
                throw new Error('Event not found');
            }

            return event.registerUser(userData.id);
        } catch (error) {
            console.error('Registration error:', error);
            throw error;
        } finally {
            UIManager.hideLoading();
        }
    }

    // Public methods
    return {
        // Add new event with validation
        addEvent: function(eventData) {
            try {
                const { name, date, category } = eventData;
                if (!name || !date || !category) {
                    throw new Error('Missing required event fields');
                }

                const newEvent = new Event({
                    ...eventData,
                    id: events.length + 1
                });
                events.push(newEvent);
                return newEvent;
            } catch (error) {
                console.error('Error adding event:', error);
                throw error;
            }
        },

        // Register user for an event
        registerUser: async function(eventId, userData) {
            try {
                return await registerUserAsync(eventId, userData);
            } catch (error) {
                UIManager.showError('Registration failed: ' + error.message);
                throw error;
            }
        },

        // Get event details including availability
        getEventDetails: function(eventId) {
            const event = events.find(e => e.id === eventId);
            if (!event) {
                throw new Error('Event not found');
            }

            const eventJSON = event.toJSON();
            return {
                ...eventJSON,
                availability: event.checkAvailability()
            };
        },

        // Filter events by category with custom filter function
        filterEventsByCategory: function(category, customFilter = null) {
            const filteredEvents = [...events].filter(event => {
                const categoryMatch = category ? event.category === category : true;
                return customFilter ? categoryMatch && customFilter(event) : categoryMatch;
            });
            return filteredEvents.map(event => event.toJSON());
        },

        // Get registration statistics
        getRegistrationStats: function() {
            return registrationTracker.getAllStats();
        },

        // Get all events (for read-only purposes)
        getAllEvents: function() {
            return [...events].map(event => event.toJSON());
        },

        // Search events using higher-order function
        searchEvents: function(searchFn) {
            return [...events].filter(searchFn).map(event => event.toJSON());
        },

        // Get events by category using filter
        getEventsByCategory: function(category) {
            return [...events]
                .filter(event => event.category === category)
                .map(event => event.toJSON());
        },

        // Get formatted event cards using map
        getFormattedEventCards: function() {
            return events.map(({ id, name, date, location, presenter, seats, category, description }) => ({
                id,
                title: `${name} - ${category}`,
                details: `${formatDate(date)} at ${location}`,
                presenter,
                availability: `${seats} seats available`,
                category,
                description
            }));
        },

        // Get event statistics using reduce
        getEventStatistics: function() {
            return events.reduce((stats, { category, seats, date }) => ({
                ...stats,
                byCategory: {
                    ...stats.byCategory,
                    [category]: (stats.byCategory[category] || 0) + 1
                },
                totalSeats: stats.totalSeats + seats,
                upcomingEvents: date >= new Date() ? stats.upcomingEvents + 1 : stats.upcomingEvents
            }), {
                byCategory: {},
                totalSeats: 0,
                upcomingEvents: 0,
                totalEvents: events.length
            });
        },

        // Initialize events
        initializeEvents: async function() {
            try {
                const loadedEvents = await loadEventsAsync();
                UIManager.updateEventDisplay(loadedEvents.map(event => event.toJSON()));
                return loadedEvents;
            } catch (error) {
                UIManager.showError('Failed to load events. Please try again later.');
                throw error;
            }
        }
    };
})();

// UI Management Module
const UIManager = (function() {
    // Keep track of current filter and search state
    let currentFilter = '';
    let currentSearch = '';
    
    const formatDate = dateString => {
        try {
            const options = { year: 'numeric', month: 'long', day: 'numeric' };
            return new Date(dateString).toLocaleDateString(undefined, options);
        } catch (error) {
            console.error('Error formatting date:', error);
            return dateString;
        }
    };

    const createSearchBar = () => {
        const searchContainer = document.createElement('div');
        searchContainer.className = 'search-container';

        const searchInput = document.createElement('input');
        Object.assign(searchInput, {
            type: 'text',
            placeholder: 'Search events...',
            className: 'search-input'
        });

        // Add keydown event listener for real-time search
        let searchTimeout;
        searchInput.addEventListener('keydown', ({ target: { value } }) => {
            clearTimeout(searchTimeout);
            searchTimeout = setTimeout(() => {
                currentSearch = value.toLowerCase();
                handleSearchAndFilter();
            }, 300);
        });

        searchContainer.appendChild(searchInput);
        return searchContainer;
    };

    const createCategoryFilter = (categories, selectedCategory = '') => {
        const container = document.createElement('div');
        container.className = 'category-filter';

        const select = document.createElement('select');
        select.addEventListener('change', ({ target: { value } }) => {
            currentFilter = value;
            handleSearchAndFilter();
        });

        const options = [
            { value: '', text: 'All Categories' },
            ...categories.map(category => ({ value: category, text: category }))
        ];

        select.innerHTML = options
            .map(({ value, text }) => `
                <option value="${value}" ${value === selectedCategory ? 'selected' : ''}>
                    ${text}
                </option>
            `)
            .join('');

        container.appendChild(select);
        return container;
    };

    const createEventCard = event => {
        const { id, name, category, location, presenter, description, date, seats } = event;
        const availability = event.availability || { available: seats > 0 };
        
        // Create card using jQuery
        const $card = $('<div>', {
            class: `event-card event-details ${availability.available ? 'available' : 'full'}`,
            'data-event-id': id
        });

        $card.html(`
            <h2>${name}</h2>
            <p class="event-category">${category}</p>
            <p class="event-location"><i>Location: ${location}</i></p>
            ${presenter ? `<p class="event-presenter">Presenter: ${presenter}</p>` : ''}
            <p class="event-description">${description}</p>
            <p>Date: ${formatDate(date)}</p>
            <p class="seats-count">Available Seats: ${seats}</p>
            ${availability.waitlistLength ? 
                `<p class="waitlist-count">Waitlist: ${availability.waitlistLength}</p>` : 
                ''}
            <button ${!availability.available ? 'disabled' : ''}>
                ${availability.available ? 'Register' : 'Full - Join Waitlist'}
            </button>
        `);

        // Add jQuery event handlers
        $card.find('h2').on('click', () => showEventDetails(event));
        
        const $button = $card.find('button');
        if (availability.available) {
            $button
                .hover(
                    function() { $(this).text('Click to Register'); },
                    function() { $(this).text('Register'); }
                )
                .on('click', e => {
                    e.preventDefault();
                    handleRegistration(id);
                });
        }

        $card.on('dblclick', e => {
            if (availability.available && !$(e.target).is('button')) {
                handleRegistration(id);
            }
        });

        // Add fade-in animation
        $card.hide().fadeIn(500);

        return $card;
    };

    const createStatisticsElement = ({ totalEvents, upcomingEvents, totalSeats }) => {
        const container = document.createElement('div');
        container.className = 'event-statistics';
        
        container.innerHTML = `
            <p>Total Events: ${totalEvents}</p>
            <p>Upcoming Events: ${upcomingEvents}</p>
            <p>Total Available Seats: ${totalSeats}</p>
        `;

        return container;
    };

    function showEventDetails(event) {
        const modal = document.createElement('div');
        modal.className = 'event-modal';
        
        const content = document.createElement('div');
        content.className = 'modal-content';
        
        const closeBtn = document.createElement('span');
        closeBtn.className = 'close-button';
        closeBtn.textContent = '×';
        closeBtn.onclick = () => modal.remove();
        
        content.innerHTML = `
            <h2>${event.name}</h2>
            <p class="event-category">${event.category}</p>
            <p><strong>Date:</strong> ${formatDate(event.date)}</p>
            <p><strong>Location:</strong> ${event.location}</p>
            <p><strong>Presenter:</strong> ${event.presenter}</p>
            <p><strong>Available Seats:</strong> ${event.seats}</p>
            <p>${event.description}</p>
        `;
        
        content.insertBefore(closeBtn, content.firstChild);
        modal.appendChild(content);
        document.body.appendChild(modal);
        
        // Close modal when clicking outside
        modal.addEventListener('click', (e) => {
            if (e.target === modal) modal.remove();
        });
        
        // Close modal with Escape key
        document.addEventListener('keydown', function closeModal(e) {
            if (e.key === 'Escape') {
                modal.remove();
                document.removeEventListener('keydown', closeModal);
            }
        });
    }

    // Combined search and filter handler
    const handleSearchAndFilter = () => {
        const allEvents = EventManager.getAllEvents();
        const filteredEvents = allEvents.filter(event => {
            const matchesCategory = !currentFilter || event.category === currentFilter;
            const matchesSearch = !currentSearch || 
                Object.values(event)
                    .some(value => 
                        String(value)
                            .toLowerCase()
                            .includes(currentSearch)
                    );
            return matchesCategory && matchesSearch;
        });

        updateEventDisplay(filteredEvents);
    };

    function createLoadingSpinner() {
        const spinner = document.createElement('div');
        spinner.className = 'loading-spinner';
        spinner.innerHTML = `
            <div class="spinner"></div>
            <p>Loading...</p>
        `;
        return spinner;
    }

    return {
        updateEventDisplay: async function(events = EventManager.getAllEvents()) {
            try {
                this.showLoading();
                await new Promise(resolve => setTimeout(resolve, 300));
                
                const $eventSection = $('section');
                $eventSection.empty();

                const $searchBar = $(createSearchBar());
                $searchBar.appendTo($eventSection);

                const validEvents = events.filter(event => new Date(event.date) >= new Date());
                
                if (validEvents.length === 0) {
                    $('<p>')
                        .text(currentSearch || currentFilter ? 
                            'No events found matching your criteria.' : 
                            'No upcoming events available at this time.')
                        .appendTo($eventSection);
                    return;
                }
                
                const stats = EventManager.getEventStatistics();
                const $statsElement = $(createStatisticsElement(stats));
                $statsElement.appendTo($eventSection);
                
                const categories = [...new Set(events.map(event => event.category))];
                const $filterElement = $(createCategoryFilter(categories, currentFilter));
                $filterElement.appendTo($eventSection);
                
                const $eventsContainer = $('<div>', { class: 'events-container' });
                
                validEvents.forEach(event => {
                    const details = EventManager.getEventDetails(event.id);
                    const $cardElement = createEventCard(details);
                    $eventsContainer.append($cardElement);
                });
                
                $eventsContainer.appendTo($eventSection).hide().fadeIn(500);

            } catch (error) {
                this.showError('Error updating display: ' + error.message);
            } finally {
                this.hideLoading();
            }
        },

        setupKeyboardNavigation: function() {
            document.addEventListener('keydown', (e) => {
                if (e.ctrlKey && e.key === 'f') {
                    e.preventDefault();
                    document.querySelector('.search-input')?.focus();
                }
            });
        },

        // Method to update a single event card
        updateEventCard: function(eventId) {
            try {
                const eventDetails = EventManager.getEventDetails(eventId);
                const $existingCard = $(`[data-event-id="${eventId}"]`);
                
                if ($existingCard.length) {
                    const $newCard = createEventCard(eventDetails);
                    $existingCard.fadeOut(300, function() {
                        $(this).replaceWith($newCard);
                        $newCard.hide().fadeIn(300);
                    });
                }
            } catch (error) {
                console.error('Error updating event card:', error);
            }
        },

        showLoading: function() {
            const $existingSpinner = $('.loading-spinner');
            if (!$existingSpinner.length) {
                $('<div>', { class: 'loading-spinner' })
                    .html(`
                        <div class="spinner"></div>
                        <p>Loading...</p>
                    `)
                    .appendTo('body')
                    .fadeIn(300);
            }
        },

        hideLoading: function() {
            $('.loading-spinner').fadeOut(300, function() {
                $(this).remove();
            });
        },

        showError: function(message) {
            $('<div>', { class: 'notification error' })
                .text(message)
                .appendTo('body')
                .fadeIn(300)
                .delay(4700)
                .fadeOut(300, function() {
                    $(this).remove();
                });
        }
    };
})();

// Update global event handlers
async function handleRegistration(eventId) {
    try {
        UIManager.showLoading();
        const result = await EventManager.registerUser(eventId, { id: 'user-' + Date.now() });
        
        // Update UI
        UIManager.updateEventCard(eventId);
        
        // Show success notification
        const notification = document.createElement('div');
        notification.className = 'notification success';
        notification.textContent = 'Successfully registered for event!';
        document.body.appendChild(notification);
        setTimeout(() => notification.remove(), 3000);
    } catch (error) {
        UIManager.showError('Registration failed: ' + error.message);
    } finally {
        UIManager.hideLoading();
    }
}

function handleCategoryFilter(category) {
    const filteredEvents = EventManager.filterEventsByCategory(category, 
        event => event.checkAvailability().isUpcoming
    );
    UIManager.updateEventDisplay(filteredEvents);
}

// Form Handling Module
const FormManager = (function() {
    // Debug logger
    const debug = {
        log: (message, data = null) => {
            console.log(`%c[Form Debug] ${message}`, 'color: #2196F3', data || '');
        },
        error: (message, error = null) => {
            console.error(`%c[Form Error] ${message}`, 'color: #f44336', error || '');
        },
        warn: (message, data = null) => {
            console.warn(`%c[Form Warning] ${message}`, 'color: #ff9800', data || '');
        },
        group: (name) => {
            console.group(`%c[Form Debug] ${name}`, 'color: #2196F3');
        },
        groupEnd: () => console.groupEnd()
    };

    // Form validation rules
    const validationRules = {
        name: {
            pattern: /^[A-Za-z ]{2,}$/,
            message: 'Please enter a valid name (minimum 2 characters, letters only)'
        },
        email: {
            pattern: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
            message: 'Please enter a valid email address'
        },
        eventSelect: {
            validate: value => value !== '',
            message: 'Please select an event'
        }
    };

    // Validate a single form field with debugging
    const validateField = (field) => {
        const { name, value } = field;
        debug.group(`Validating field: ${name}`);
        debug.log('Field value:', value);

        const rule = validationRules[name];
        const errorElement = document.getElementById(`${name}Error`);
        const formGroup = field.closest('.form-group');
        
        let isValid = true;
        
        if (rule) {
            isValid = rule.pattern ? 
                rule.pattern.test(value) : 
                rule.validate(value);
            
            debug.log(`Validation rule applied:`, {
                pattern: rule.pattern?.toString() || 'custom validation',
                isValid
            });
        }

        if (!isValid || !value) {
            formGroup.classList.add('error');
            errorElement.classList.add('visible');
            debug.warn(`Validation failed for ${name}`, { value, rule: rule.message });
        } else {
            formGroup.classList.remove('error');
            errorElement.classList.remove('visible');
            debug.log(`Validation passed for ${name}`);
        }

        debug.groupEnd();
        return isValid;
    };

    // Handle API communication with debugging
    const submitRegistration = async (formData) => {
        debug.group('Submitting Registration');
        debug.log('Registration payload:', formData);

        try {
            const response = await fetch('/api/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            }).catch(error => {
                debug.warn('Fetch failed, falling back to mock API', error);
                return EventAPI.registerUser(formData);
            });

            debug.log('Raw response:', response);

            // If we got a real response, handle it
            if (response instanceof Response) {
                if (!response.ok) {
                    const errorText = await response.text();
                    debug.error('API Error Response:', errorText);
                    throw new Error('Registration failed: ' + response.statusText);
                }
                const jsonResponse = await response.json();
                debug.log('API Success Response:', jsonResponse);
                return jsonResponse;
            }

            // If we're using the mock API, the response is already processed
            debug.log('Mock API Response:', response);
            return response;
        } catch (error) {
            debug.error('Registration submission failed', error);
            throw error;
        } finally {
            debug.groupEnd();
        }
    };

    // Initialize form event handlers with jQuery
    const initializeForm = () => {
        debug.group('Initializing Form');
        
        const $form = $('#registrationForm');
        const $successMessage = $('#successMessage');
        
        if (!$form.length) {
            debug.error('Registration form not found in DOM');
            return;
        }

        // Populate event select with jQuery
        const $eventSelect = $form.find('[name="eventSelect"]');
        const events = EventManager.getAllEvents();
        
        debug.log('Available events for registration:', events);

        events
            .filter(event => new Date(event.date) >= new Date())
            .forEach(event => {
                $('<option>', {
                    value: event.id,
                    text: `${event.name} - ${formatDate(event.date)}`
                }).appendTo($eventSelect);
            });

        // Add input event listeners with jQuery
        Object.keys(validationRules).forEach(fieldName => {
            const $field = $form.find(`[name="${fieldName}"]`);
            if ($field.length) {
                $field
                    .on('input blur', function() {
                        validateField(this);
                    });
                debug.log(`Added validation listeners to ${fieldName}`);
            } else {
                debug.warn(`Field not found: ${fieldName}`);
            }
        });

        // Handle form submission with jQuery
        $form.on('submit', async function(e) {
            e.preventDefault();
            debug.group('Form Submission');
            
            try {
                const $submitButton = $('#registerBtn');
                const formData = new FormData(this);
                const formFields = Object.fromEntries(formData.entries());
                debug.log('Form data collected:', formFields);
                
                // Validate all fields
                const validations = Object.keys(validationRules).map(fieldName => {
                    const field = this.elements[fieldName];
                    return validateField(field);
                });

                debug.log('Form validation results:', validations);

                if (validations.every(Boolean)) {
                    // Show loading state
                    UIManager.showLoading();

                    // Prepare registration data
                    const registrationData = {
                        name: formFields.name,
                        email: formFields.email,
                        eventId: parseInt(formFields.eventSelect)
                    };

                    debug.log('Prepared registration data:', registrationData);

                    // Submit registration
                    const result = await submitRegistration(registrationData);
                    debug.log('Registration API response:', result);

                    // Register for the event if API call was successful
                    const eventRegistration = await EventManager.registerUser(
                        registrationData.eventId,
                        { 
                            id: result.data.userId,
                            name: registrationData.name,
                            email: registrationData.email
                        }
                    );
                    debug.log('Event registration result:', eventRegistration);

                    // Show success message with API response
                    $successMessage
                        .text(`${result.message} Your registration ID is: ${result.data.userId}`)
                        .fadeIn(300);
                    
                    this.reset();

                    // Update the event display
                    UIManager.updateEventCard(registrationData.eventId);

                    // Create success notification with jQuery
                    $('<div>', { class: 'notification success' })
                        .html(`
                            <strong>Registration Successful!</strong><br>
                            Event: ${result.data.eventName}<br>
                            Registration ID: ${result.data.userId}
                        `)
                        .appendTo('body')
                        .fadeIn(300)
                        .delay(4700)
                        .fadeOut(300, function() {
                            $(this).remove();
                        });

                } else {
                    debug.warn('Form validation failed');
                }
            } catch (error) {
                debug.error('Unexpected form submission error:', error);
                
                // Show error with jQuery
                UIManager.showError(`Registration failed: ${error.message}`);
                
                $('<div>', { 
                    class: 'error-message visible',
                    text: error.message
                })
                    .prependTo($form)
                    .fadeIn(300)
                    .delay(4700)
                    .fadeOut(300, function() {
                        $(this).remove();
                    });
            } finally {
                $('#registerBtn')
                    .prop('disabled', false)
                    .text('Register for Event');
                
                $form.removeClass('processing');
                debug.groupEnd();
            }
        });

        debug.log('Form initialization complete');
        debug.groupEnd();
    };

    return {
        initialize: initializeForm,
        debug
    };
})();

// Add global error handler for uncaught promises
window.addEventListener('unhandledrejection', event => {
    console.error('[Unhandled Promise Rejection]', event.reason);
    UIManager.showError('An unexpected error occurred. Please try again.');
});

// Add performance monitoring
const performance = {
    marks: {},
    start: (name) => {
        performance.marks[name] = performance.now();
        console.log(`%c[Performance] Started: ${name}`, 'color: #9c27b0');
    },
    end: (name) => {
        if (performance.marks[name]) {
            const duration = performance.now() - performance.marks[name];
            console.log(`%c[Performance] ${name}: ${duration.toFixed(2)}ms`, 'color: #9c27b0');
            delete performance.marks[name];
        }
    }
};

// Initialize page
document.addEventListener('DOMContentLoaded', async () => {
    try {
        await EventManager.initializeEvents();
        FormManager.initialize();
    } catch (error) {
        console.error('Error initializing page:', error);
    }
}); 