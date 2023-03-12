delete from gacha_info;
insert into gacha_info (id, gacha_id, gacha_name, banner_image, exec_count, created) values
    (1, 1, "name", "https://hoge.png", 1, "2022-01-01 00:00:00")
;

delete from gacha_cost;
insert into gacha_cost (id, gacha_id, cost_type, cost, created) values
    (1, 1, 1, 1, "2022-01-01 00:00:00")
;

delete from gacha_probability;
insert into gacha_probability (id, gacha_id, probability, object_type, object_id, object_count, created) values
    (1, 1, 100, 1, 1, 1, "2022-01-01 00:00:00")
;
