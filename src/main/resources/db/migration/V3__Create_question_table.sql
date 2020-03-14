create table question
(
	id int auto_increment,
	title varchar(50),
	content varchar(500),
	read int,
	comment int,
	gmt_create BIGINT,
	gmt_modified BIGINT,
	tag varchar(100),
	type varchar(100),
	constraint question_pk
		primary key (id)
);

