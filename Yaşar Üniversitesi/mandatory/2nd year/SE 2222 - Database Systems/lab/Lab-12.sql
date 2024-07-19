/* 1 */
SELECT ENAME, SAL
FROM emp
WHERE SAL > (
		SELECT SAL
        FROM emp
        WHERE EMPNO = 7566
		);
        
/* 2 */
SELECT EMPNO, ENAME
FROM emp
WHERE SAL > (	
	SELECT avg(sal)
    from emp
	);
    
/* 3 */
SELECT ENAME
from emp
WHERE empno in (
	select mgr
    from emp
);

/* 4 */
SELECT ENAME, SAL
from emp
WHERE SAL > ALL(
		SELECT avg(sal)
        from emp
        group by DEPTNO
		);
        
/* 5 */
SELECT ENAME, JOB
from emp
WHERE JOB NOT like 'CLERK' AND SAL < ANY(
	SELECT SAL
    from emp
    WHERE JOB like "CLERK"
	);
    
/* 6 */
INSERT INTO dept VALUES(50, 'SOFTWARE', null);

/* 7 */
INSERT INTO emp (EMPNO, ENAME, HIREDATE) VALUES(1234, "EMPLOYEE1", CURRENT_DATE());

/* 8 */
INSERT INTO bonus (
		SELECT ENAME, JOB, SAL, COMM
		FROM emp
        WHERE COMM is not NULL
        );
        
        


/* 9 */
UPDATE dept
SET LOC = 'IZMIR'
WHERE DNAME LIKE 'SOFTWARE';

/* 10 */
UPDATE emp
SET SAL = SAL + SAL/10;

/* 11 */
DELETE FROM emp 
WHERE EMPNO = 1234;

/* 12 */
DELETE FROM dept 
WHERE DEPTNO NOT IN (
	SELECT DEPTNO
    from emp
    );


