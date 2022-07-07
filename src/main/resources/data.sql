insert into sprints(name, date_start, date_end, description, status)
VALUES ('first sprint', '2022-07-07', '2022-07-28', 'first sprint description', 'IN_PROGRESS');

insert into User_Stories(name, description, points, status) VALUES
    ('first userStory', 'first userStory description', 1, 'TO_DO'),
    ('second userStory', 'second userStory description', 2, 'IN_PROGRESS'),
    ('third userStory', 'third userStory description', 4, 'REVIEW'),
    ('fourth userStory', 'fourth userStory description', 8, 'DONE');

insert into Sprint_User_Story(sprint_id, user_story_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4);