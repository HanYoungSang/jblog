-- 카테고리 seq

drop sequence seq_category;

create SEQUENCE seq_category
start with 1
increment by 1
MAXVALUE 9999999999;
;


-- 포스트 seq
drop sequence seq_post;

create SEQUENCE seq_post
start with 1
increment by 1
MAXVALUE 9999999999;
;