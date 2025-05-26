# Bootstrap 5 Structure and Files

## Directory Structure
When you download or install Bootstrap, you'll find the following important directories:

### 1. CSS Directory (`dist/css/`)
- `bootstrap.css` - The unminified CSS file
- `bootstrap.min.css` - The minified CSS file for production
- `bootstrap.css.map` - Source map for development tools
- `bootstrap.rtl.css` - Right-to-left support CSS
- `bootstrap.rtl.min.css` - Minified RTL CSS

### 2. JavaScript Directory (`dist/js/`)
- `bootstrap.bundle.js` - Unminified JS with Popper.js included
- `bootstrap.bundle.min.js` - Minified JS with Popper.js for production
- `bootstrap.js` - Core JavaScript without Popper.js
- `bootstrap.min.js` - Minified core JavaScript
- `.map` files - Source maps for debugging

### 3. Icons (`dist/icons/`)
- SVG icons that can be included in your project
- Available in multiple formats (SVG, web font)
- Can be styled with CSS

## Purpose of Each Component

### CSS Files
- Provides all the styling for Bootstrap components
- Includes grid system, typography, components, and utilities
- RTL files support right-to-left languages
- Minified versions reduce file size for production

### JavaScript Files
- Provides interactive functionality for components
- `bootstrap.bundle.js` includes:
  - Dropdowns
  - Modals
  - Tooltips
  - Popovers
  - Collapse functionality
  - And more...
- Requires Popper.js for certain components (included in bundle)

### Icons
- Provides scalable vector icons
- Can be used in navigation, buttons, forms
- Customizable with CSS
- Accessible and lightweight

## Best Practices
1. Use minified files in production
2. Include JavaScript bundle at the end of body
3. Include required dependencies (Popper.js)
4. Use source maps during development
5. Consider loading files from CDN for better caching 