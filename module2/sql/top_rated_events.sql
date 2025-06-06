SELECT 
    e.event_id,
    e.title AS event_title,
    COUNT(f.feedback_id) AS total_feedbacks,
    AVG(f.rating) AS average_rating
FROM 
    Events e
JOIN 
    Feedback f ON e.event_id = f.event_id
GROUP BY 
    e.event_id, e.title
HAVING 
    COUNT(f.feedback_id) >= 10
ORDER BY 
    average_rating DESC;
