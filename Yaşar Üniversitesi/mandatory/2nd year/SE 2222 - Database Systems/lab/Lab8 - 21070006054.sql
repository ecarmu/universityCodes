/*1)
SELECT UPPER(emp.ENAME), LOWER(emp.JOB)
FROM emp;*/

/*
2)
SELECT SUBSTR(emp.ENAME,1, 3)
FROM emp;
*/

/*3)
SELECT CONCAT(ename, '/', job) AS EMPLOYEES
FROM emp;
*/

/*
4)
SELECT ename, LENGTH(ename) AS length
FROM emp;
*/

/*
5)
SELECT emp.ENAME, dept.DNAME
FROM emp, dept
WHERE emp.DEPTNO = dept.DEPTNO
*/

/*
6)
SELECT emp.ENAME, dept.DNAME
FROM emp
RIGHT OUTER JOIN dept ON emp.DEPTNO = dept.DEPTNO
*/

/*
7)
SELECT emp.ENAME, dept.DNAME
FROM emp
RIGHT OUTER JOIN dept ON emp.DEPTNO = dept.DEPTNO
WHERE ENAME IS NULL
/*

8)
SELECT emp.ENAME, emp.SAL, salgrade.GRADE
FROM emp, salgrade
WHERE SAL BETWEEN LOSAL AND HISAL;
*/

/*
9)
SELECT e.ENAME AS employee, m.ENAME AS manager
FROM emp e, emp m
WHERE e.MGR = m.EMPNO;
*/

/*
10)
SELECT EMPNO, ENAME, JOB, SAL, COMM, emp.DEPTNO, DNAME
FROM emp, dept
WHERE COMM IS NOT NULL AND emp.DEPTNO = dept.DEPTNO
*/
