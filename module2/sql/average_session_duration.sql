SELECT
    e.event_id,
    e.title AS event_title,
    AVG(TIMESTAMPDIFF(MINUTE, s.start_time, s.end_time)) AS avg_session_duration_minutes
FROM
    Events e
JOIN
    Sessions s ON e.event_id = s.event_id
GROUP BY
    e.event_id, e.title
ORDER BY
    avg_session_duration_minutes DESC;
