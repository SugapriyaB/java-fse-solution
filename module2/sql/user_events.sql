SELECT 
    u.user_id,
    u.full_name,
    e.event_id,
    e.title AS event_title,
    e.city AS event_city,
    e.start_date,
    e.end_date
FROM 
    Users u
JOIN 
    Registrations r ON u.user_id = r.user_id
JOIN 
    Events e ON r.event_id = e.event_id
WHERE 
    e.status = 'upcoming'
    AND u.city = e.city
ORDER BY 
    e.start_date;
