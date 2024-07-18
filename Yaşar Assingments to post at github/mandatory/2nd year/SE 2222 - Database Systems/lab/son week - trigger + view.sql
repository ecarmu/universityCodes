-- 1 --
CREATE view HighSal as 
select empno, ename, sal
from emp
where sal > 2000;

-- 2 --
insert into highsal(empno, ename, sal) values(1234, 'Employee1', 5000);

-- 3 -- 
select *
from highsal;

-- 4 --
select *
from emp
where sal > 2000;

-- 5 --
create view DeptSal as
select deptno, sum(sal) as sum_sal
from emp
group by deptno;


-- 6 -- 
select dept.dname, sum_sal
from deptsal, dept
where dept.deptno = deptsal.DEPTNO AND sum_sal > 9000;


-- 7 --
create table LOGEMP(
EMPNO DECIMAL(4,0),
ENAME VARCHAR(10),
LOGDATE DATE,
LOGTYPE VARCHAR(10)
);

-- 8 -- 
create trigger emp_trigger
after insert
on emp
for each row 	
INSERT into LOGEMP(empno, ename, logdate, logtype) values(new.empno, new.ename, current_date(), "insert");

-- 9 --
create trigger emp_trigger
after delete
on emp
for each row 	
INSERT into LOGEMP(empno, ename, logdate, logtype) values(old.empno, old.ename, current_date(), "delete");

-- 10 --
create trigger emp_trigger
after update
on emp
for each row 	
INSERT into LOGEMP(empno, ename, logdate, logtype) values(new.empno, new.ename, current_date(), "update");