INSERT INTO tag
(
    id,
    name
)
VALUES
       (
        1,
        'summer'
        ),
       (
        2,
        'winter'
       ),
       (
        3,
        'spring'
       ),
       (
        4,
        'autumn'
       );
INSERT INTO certificate
(
 id,
 name,
 description,
 price,
 duration,
 create_date,
 last_update_date
)
VALUES
(
 1,
 'Holiday certificate',
 'The best holidays in your life!',
 1000,
 14,
 '2005-08-09T18:31:42.201Z',
 '2005-08-09T18:31:42.201Z'
);

INSERT INTO certificate_tag
(
 certificate_id,
 tag_id
)
VALUES
(
 1,
 1
)