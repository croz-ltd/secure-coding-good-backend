INSERT INTO auth_user(username, user_type, password, security_question_one, security_question_two, security_question_three)
VALUES
    ('john.doe', 'BUYER', '$2a$10$tHrBt0dxnAPHYn8O55BNeOfSYLahaK5uyOxBZ2VX5IAkQ68qee58m', 'Cityville', 'High School A', 'Park'),
    ('alice.smith', 'BUYER', '$2a$10$6JI..F1Occ/zBydZKZ8Li.5ItQl/4w2NhOKrg4QAvOJEjhBjJ7NZq', 'Townsville', 'High School B', 'Beach'),
    ('david.jones', 'BUYER', '$2a$10$ouAVLQSEhvIMyFQAVF.e0u4RI2SIbVf.XtoeMGsbvdwmFP9GtmqtS', 'Villagetown', 'High School C', 'Library'),
    ('emily.brown', 'BUYER', '$2a$10$Le.dvmOiDJo7yInpOvxYiuCm29un3KzKWiOcoquL0E3E4N7OxlQp.', 'Hamletsville', 'High School D', 'Coffee Shop'),
    ('chris.wilson', 'BUYER', '$2a$10$t9rckjA0FCGwforcTKi.T.CV3Xfs1FLxtJiDgzfB6VkBs.Ah1IO.i', 'Suburbia', 'High School E', 'Gym');

INSERT INTO user_buyer(id, address)
VALUES
    (1, '123 Main St, Cityville'),
    (2, '456 Oak St, Townsville'),
    (3, '789 Pine St, Villagetown'),
    (4, '101 Elm St, Hamletsville'),
    (5, '202 Maple St, Suburbia');

INSERT INTO auth_user(username, user_type, password, security_question_one, security_question_two, security_question_three)
VALUES
    ('sarah.miller', 'SELLER', '$2a$10$IfoAelLFjMwBEGq6j34sOe.el1e5A0XzvcQREvrZMhasJ083xKaA2', 'Ruralville', 'High School F', 'Grocery Store'),
    ('jason.white', 'SELLER', '$2a$10$4CIOU4VQkHaXEf/1bRBrXOLDOBZKKXYuyK5iZQ3YogBU1fZ80jKqu', 'Downtown', 'High School G', 'Movie Theater'),
    ('lisa.green', 'SELLER', '$2a$10$JkGRMEHrHVUEOBJGDoh6BeQ1bi1bW80zzBSP8tREHk54zZC0s303C', 'Mountainside', 'High School H', 'Park'),
    ('kevin.jenkins', 'SELLER', '$2a$10$UysFUi7NWYGHiVTxCgv8ketANrXrQg/TmxdLKL1kXS3PqycNGcM56', 'Lakeside', 'High School I', 'Beach'),
    ('laura.smith', 'SELLER', '$2a$10$p7KAM4M9JZt4ox2qtFjsUOezYgTxW7t71C/OZvQMej2LRKFY7uy1W', 'Countryside', 'High School J', 'Restaurant');

INSERT INTO user_seller(id, oib)
VALUES
    (6, '67890123456'),
    (7, '78901234567'),
    (8, '89012345678'),
    (9, '90123456789'),
    (10, '12398765432');

INSERT INTO auth_user(username, user_type, password, security_question_one, security_question_two, security_question_three)
VALUES ('admin', 'ADMIN', '$2a$10$096qkydajQZnEZKnf5SzAOTpL9jN03LUmfs.iQ0eYjKuD0X33diOS', 'Osijek', 'Srednja škola', 'Škola');