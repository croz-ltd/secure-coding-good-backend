INSERT INTO auth_user(username, user_type, password, security_question_one, security_question_two, security_question_three)
VALUES
    ('john.doe', 'BUYER', '$2a$10$tHrBt0dxnAPHYn8O55BNeOfSYLahaK5uyOxBZ2VX5IAkQ68qee58m', '$2a$10$hx4lHf6ZxjnsRYQQOfy72eFN5ViHFuzn98BtIdapRla3z6FN88rQK', '$2a$10$BWWJq3Oc239fkoqkB8AVk.cUujTkrl2iYsxXixzTal5Wt8xhgX86S', '$2a$10$r./yY5ynPlxzxGngqHS/ouBQrSL9T0jR3GBRq0AIIj5tmS6XEtYn6'),
    ('alice.smith', 'BUYER', '$2a$10$6JI..F1Occ/zBydZKZ8Li.5ItQl/4w2NhOKrg4QAvOJEjhBjJ7NZq', '$2a$10$ZvhFi4IVSb8VuD3A7Y/XZOW4LI/bXlTGIyj3X2.F9leC50wA6mwgi', '$2a$10$zZqD8YXpgdMUQzjkk8F15OzsMu0VpZOuFxYqfyVIN9JnYSmDqJcxq', '$2a$10$feWREdXhBWGJaqTc.Cuohuf7wK/hmF.hye8DEax2GWshhO5XTedAq'),
    ('david.jones', 'BUYER', '$2a$10$ouAVLQSEhvIMyFQAVF.e0u4RI2SIbVf.XtoeMGsbvdwmFP9GtmqtS', '$2a$10$s2Zj2nuViHn4Pv.pzhCw4u2.uwWd0qccGw9en4vkiZ8K/vhw4El.u', '$2a$10$A0mnBvhHNyiWe/SNmXRWIefWFm/W5gmxzSCiQYoyiUPtFdooqJ0gq', '$2a$10$3183wjKR.LBR6RTGmkJrC..TZ0D.FQLxs7MQUlQ7Mf4zFiNyNQI9.'),
    ('emily.brown', 'BUYER', '$2a$10$Le.dvmOiDJo7yInpOvxYiuCm29un3KzKWiOcoquL0E3E4N7OxlQp.', '$2a$10$o3NIRL2xzGJ4ndFSbzJ/EODUNUHQNnmwON2CKCEMG9Ff4/HxoVsmu', '$2a$10$Mi55jIfIJtObli3/FuORVuR.qfN73tAuDyMXXMjmlG6P/OpueA6M2', '$2a$10$ObsYLTjY.GKW2iiWnjxeauCWUnjGVfVM8H7GEM1ZZtBWDsg8Ac5di'),
    ('chris.wilson', 'BUYER', '$2a$10$t9rckjA0FCGwforcTKi.T.CV3Xfs1FLxtJiDgzfB6VkBs.Ah1IO.i', '$2a$10$y9Hj/TyT769jefv1al/oj.Ojlc3/jQku5O4R26oltBxz/8TT3JIZu', '$2a$10$nOMY3K3WaMmTgZEuGenaEeJRuNWkdJ8v8fR4igOK9O5dW2Hvk5Q0K', '$2a$10$vy8eP0x0UfP3DgV.RufGhORUuK7itCQqHx8z70osqZZuHvECmGZHO');

INSERT INTO user_buyer(id, address)
VALUES
    (1, '123 Main St, Cityville'),
    (2, '456 Oak St, Townsville'),
    (3, '789 Pine St, Villagetown'),
    (4, '101 Elm St, Hamletsville'),
    (5, '202 Maple St, Suburbia');

INSERT INTO auth_user(username, user_type, password, security_question_one, security_question_two, security_question_three)
VALUES
    ('sarah.miller', 'SELLER', '$2a$10$IfoAelLFjMwBEGq6j34sOe.el1e5A0XzvcQREvrZMhasJ083xKaA2', '$2a$10$ZLvjLX2CAAhBWcUutJ0/Q.bsB.trU.4Xvo8io2jSHWYFQKDD61sEy', '$2a$10$gxsxpwrvQz1zMoaoOFlhDOcFbL8aa0nfy4XA00Gs1.fcBungjFqta', '$2a$10$gqydH.jdI6fgTcpy57wmae/psAeK8X.Z3MASftTK1TZUx/1MldCkK'),
    ('jason.white', 'SELLER', '$2a$10$4CIOU4VQkHaXEf/1bRBrXOLDOBZKKXYuyK5iZQ3YogBU1fZ80jKqu', '$2a$10$BRJYXBUaYUqdaB3c7kfY7eqY0zVDRdUjLjyohcxc4/gYmsWKNhMYy', '$2a$10$Ehzn/fTlYd0pXDxaddp2cOBKGEKt/LGheGTGJTjDebnKpTM.RZ2L2', '$2a$10$vhjG5sSg1m64.3bWy165j.ks13Q8N.SDv1DzcW4sCrsH5x4ou1bjK'),
    ('lisa.green', 'SELLER', '$2a$10$JkGRMEHrHVUEOBJGDoh6BeQ1bi1bW80zzBSP8tREHk54zZC0s303C', '$2a$10$9jGMNUPTH6x/vamkitCNUOt2yNGETnYEZA7L6rTw0Ky7jRBGj5RDO', '$2a$10$PKA9DGv5cp8yaH6mwPzHv.ebOzHUz7D81wo0bbvhGLhmWFRpLHY3C', '$2a$10$6xv6Kl4cI1UelSAWXhVfCu4jXq4TP0lIWpuY6U/gbojoo38h/I3yO'),
    ('kevin.jenkins', 'SELLER', '$2a$10$UysFUi7NWYGHiVTxCgv8ketANrXrQg/TmxdLKL1kXS3PqycNGcM56', '$2a$10$1z/gSIFX/s5X1/VXxL4zw.SXNZRF8tVpJ36FcAnh98hNXtxbaqBr.', '$2a$10$6.v6jwamvDN.k5SkMMaa1OzF9a7QpzSg5xpTvEEgROiKm0DfNXlFq', '$2a$10$F3sQeZ9bjn0rdDgwZAh6zuM6XYUmO9/7eEEWotDZDlDE7VMVbdjWS'),
    ('laura.smith', 'SELLER', '$2a$10$p7KAM4M9JZt4ox2qtFjsUOezYgTxW7t71C/OZvQMej2LRKFY7uy1W', '$2a$10$Qbf8vp3BQW6TG8jjcVTnz.HIImqs8ChyoVsLMosJ3jaDSB7tOIh12', '$2a$10$BUU17arpU6k6Dd35ngSveerq.cZxdJVdJxttCfoUiKlBssgJ6oWG.', '$2a$10$nELRKApH7A7iPHpNQKL.veTVR34cCzvg0Cz5qHZOU6lERNP8Wxpru');

INSERT INTO user_seller(id, oib)
VALUES
    (6, '67890123456'),
    (7, '78901234567'),
    (8, '89012345678'),
    (9, '90123456789'),
    (10, '12398765432');

INSERT INTO auth_user(username, user_type, password, security_question_one, security_question_two, security_question_three)
VALUES ('zmilanovic', 'BUYER', '$2a$10$u6kW6vBfu0W8US/k5wP6EuubLGWYgRLYaWN7ADpQS6GFV.NfDs6DC', '$2a$10$diw2RWVHhAorMLRKbA7xOu6v5ukCvI4bO/54j1qVmBDAOjva4X39C', '$2a$10$uGGTsu1u6LlG/dKtUCS3ReZD4aLrH.F3HuueuT28de36XKig4uuZ.', '$2a$10$TDYRgxKprTAVnKdF.jWx1ORpHLHVkclsW3u3PzHbBYYt92bK2.RiW' );

INSERT INTO user_buyer(id, address) VALUES (11, 'pantovčak 241');

INSERT INTO auth_user(username, user_type, password, security_question_one, security_question_two, security_question_three)
VALUES ('admin', 'ADMIN', '$2a$10$096qkydajQZnEZKnf5SzAOTpL9jN03LUmfs.iQ0eYjKuD0X33diOS', '$2a$10$hUsLjGh0UJCRZYq9d44dVOZzuWLFxU.KkYa7wrBCYSsaeoBqCQrIe', '$2a$10$ja7VBxW6Phagx1kcANf1gu4zoRPJy.uslgbU6iVm5KUOrxeixb0wq', '$2a$10$rrBAxX9hP9nNswaM3BdViuB0HGn0Gw74fEvQhC8ULy7vn5ooB4sjG');