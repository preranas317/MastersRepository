create table authorities (
        username varchar(255) not null,
        authority varchar(255),
        primary key (username)
    );

    create table board1 (
        board_id int4 not null,
        sarray bytea,
        value varchar(255),
        primary key (board_id)
    );

    create table games (
        game_id int4 not null,
        AIplayer boolean not null,
        end_time timestamp,
        result varchar(255),
        start_time timestamp,
        winner_name varchar(255),
        player1 int4,
        player2 int4,
        primary key (game_id)
    );

    create table players (
        player_id int4 not null,
        authority varchar(255),
        email_id varchar(255),
        enabled boolean not null,
        password varchar(255),
        username varchar(255),
        primary key (player_id)
    );

    create table savedGames (
        saved_id int4 not null,
        saved_time timestamp,
        start_time timestamp,
        board_id int4,
        player_name int4,
        primary key (saved_id)
    );

    alter table games 
        add constraint FK_i7yk0yshppqtddgrc7gbd9hke 
        foreign key (player1) 
        references players;

    alter table games 
        add constraint FK_2eabwf5b3wtie9xcua0fcd2x3 
        foreign key (player2) 
        references players;

    alter table savedGames 
        add constraint FK_4vm6tjns4ncdyavsgycke5oxv 
        foreign key (board_id) 
        references board1;

    alter table savedGames 
        add constraint FK_f99y7s7uywv0ym3atb619s3l8 
        foreign key (player_name) 
        references players;

    create sequence hibernate_sequence;