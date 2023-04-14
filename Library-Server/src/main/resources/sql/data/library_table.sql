drop table if exists library_table;
create table library_table
(
    table_id  varchar(100) not null comment '桌子id'
        primary key,
    room_id   varchar(100) null comment '图书室id',
    name      varchar(20)  null comment '座位名称',
    x         int          null comment 'x坐标',
    y         int          null comment 'y坐标',
    direction smallint     null comment '方向',
    width     int          null comment '宽',
    height    int          null comment '高'
);

INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('1i5z4v6hovv', 'dsackacbjakcaw3', 'mnh7g429ocj', 22, 6, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('1oi7c2ldkcjta5c38rhsf', 'dsackacsvjakcaw1', 'cadbc1h0yhh', 4, 2, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('1t2ms90kupong2du1qqn9', 'dsackacsvjakcaw1', 'gkpi77wdndg', 7, 2, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('25nz83hovogd58qywvf73b', 'dsackacsvjakcaw1', 'e3s5ojzhnt8', 4, 8, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('2ejczagty08', 'dsackacbjakcaw3', 'a9wt2tn7r3w', 10, 12, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('2jl5vm757di6nlyxbz9cr9', 'dsackacsvjakcaw1', 'tc7qoo7sjrm', 0, 8, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('4dyxhgvbs5z7gl9ehc7v1h', 'dsackacsvjakcaw1', 'a3w879j2igd', 2, 8, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('4eru0pca5pgv4hhhnspln', 'dsackacsvjakcaw1', 'tbctse3g25', 5, 10, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('76sm10y24ebphut0ow43vc', 'dsackacsvjakcaw1', '3kxlsfxcy3y', 0, 5, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('8lq8xu7m789m3t174ozaip', 'dsackacsvjakcaw1', 'uusrs71fr1', 11, 5, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('8udwuzx0yafhijtne06a0b', 'dsackacsvjakcaw1', 'iutdigurmb', 2, 2, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A01', 'dsackacbjakcaw3', 'A01', 1, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A02', 'dsackacbjakcaw3', 'A02', 4, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A03', 'dsackacbjakcaw3', 'A03', 7, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A04', 'dsackacbjakcaw3', 'A04', 10, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A05', 'dsackacbjakcaw3', 'A05', 13, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A06', 'dsackacbjakcaw3', 'A06', 16, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A07', 'dsackacbjakcaw3', 'A07', 1, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A08', 'dsackacbjakcaw3', 'A08', 4, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A09', 'dsackacbjakcaw3', 'A09', 7, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A10', 'dsackacbjakcaw3', 'A10', 10, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A11', 'dsackacbjakcaw3', 'A11', 13, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A12', 'dsackacbjakcaw3', 'A06', 16, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A13', 'dsackacbjakcaw3', 'A13', 1, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A14', 'dsackacbjakcaw3', 'A14', 4, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A15', 'dsackacbjakcaw3', 'A15', 7, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A16', 'dsackacbjakcaw3', 'A16', 10, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A17', 'dsackacbjakcaw3', 'A17', 13, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A18', 'dsackacbjakcaw3', 'A06', 16, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A19', 'dsackacbjakcaw3', 'A13', 1, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A20', 'dsackacbjakcaw3', 'A14', 4, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A21', 'dsackacbjakcaw3', 'A15', 7, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A22', 'dsackacbjakcaw3', 'A16', 10, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A23', 'dsackacbjakcaw3', 'A17', 13, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A24', 'dsackacbjakcaw3', 'A18', 16, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A25', 'dsackacbjakcaw3', 'A06', 19, 6, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A26', 'dsackacbjakcaw3', 'A06', 19, 9, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A27', 'dsackacbjakcaw3', 'A06', 4, 12, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A28', 'dsackacbjakcaw3', 'A18', 1, 12, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A29', 'dsackacbjakcaw3', 'A16', 19, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A30', 'dsackacbjakcaw3', 'A17', 22, 3, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A31', 'dsackacbjakcaw3', 'A18', 19, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('A32', 'dsackacbjakcaw3', 'A18', 22, 0, 0, 1, 2);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('b7b8vsyhlsb78c5ufjjq5', 'dsackacsvjakcaw1', 'iescm3m7mf', 5, 10, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('cfwtyb2cz7o', 'dsackacbjakcaw3', '47y61iosabc', 16, 12, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('dd8g5nozwppwz01c9pfb5h', 'dsackacsvjakcaw1', 'cy2murrt43o', 4, 5, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('dozgw9bg0dw97k3fjldyas', 'dsackacsvjakcaw1', 'rckljxz1stc', 7, 5, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('h4euiefawbbyolnn1ddtt8', 'dsackacbjakcdfw4', 'yzsid7vzdmg', 5, 0, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('hdjtxpcgyqhbab7lqhzt5s', 'dsackacbjakcdfw4', 'yemviu1ay7m', 1, 0, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('hw9m6gn640p', 'dsackacbjakcaw3', 'u6cl1x7lqpr', 7, 12, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('iig7q2i8ojo8v7sosxrqpg', 'dsackacsvjakcaw1', 'boygb8caxzm', 11, 2, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('kdmwzqwg46k56mdoko2bf8', 'dsackacsvjakcaw1', '3mvwgetzfvj', 9, 5, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('m3ogwh009ba6cy1gr0azr', 'dsackacsvjakcaw1', '509fvy1ivel', 0, 2, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('mmmgkfrazb', 'dsackacbjakcaw3', 'k6vgeinkwdf', 22, 9, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('qdv1qqux3tobc7askf7mnn', 'dsackacsvjakcaw1', 'nufh1k1qfj9', 7, 8, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('rwrguhjbzdq1v58dzwnxok', 'dsackacbjakcdfw4', '62o9m7tx2fh', 5, 2, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('sggwpayyahp', 'dsackacbjakcaw3', 'jqrf6jabc2h', 19, 12, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('svbzx38c2za4mwhwl67y1v', 'dsackacsvjakcaw1', 't9nc71psck8', 9, 2, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('vq9lcdrzphms1hg1iioe4', 'dsackacsvjakcaw1', 'lfncw1eal5', 2, 5, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('wjwlqddqmjzdudogrywco', 'dsackacbjakcdfw4', '96ym1r9lzhw', 1, 2, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('wq9y2qgwrln', 'dsackacbjakcaw3', 'gftnwg8f1wu', 22, 12, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('wvzyfahk24oluzh4gppi6e', 'dsackacsvjakcaw1', 'u69axy2fns', 11, 8, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('wzws9qpantn', 'dsackacbjakcaw3', 'so4zh7js67', 13, 12, 0, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('zfl45vka7yazcvm4tzs4hb', 'dsackacsvjakcaw1', 'mrbpcufmhw', 9, 8, 1, 1, 1);
INSERT INTO library.library_table (table_id, room_id, name, x, y, direction, width, height)
VALUES ('zso8k5ni0hgei7qzt05ql', 'dsackacsvjakcaw1', '8hhwomdimwl', 6, 10, 1, 1, 1);
