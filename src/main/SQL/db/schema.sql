create table portfolio (
    ticker VARCHAR(6) NOT NULL UNIQUE,
    share_price double precision,
    shares integer,
    PRIMARY KEY(ticker)
);

insert into portfolio values 
('MSFT', 208.10, 15),
('TSLA', 1650.30, 10),
('TST', 101.01, 14);