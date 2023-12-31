SELECT
	client.NAME,
	cp.PROJECT_COUNT
FROM
	( SELECT CLIENT_ID, COUNT( ID ) AS PROJECT_COUNT FROM project GROUP BY CLIENT_ID ) AS cp
	INNER JOIN client ON cp.CLIENT_ID = client.ID
WHERE
	cp.PROJECT_COUNT = (
	SELECT
		MAX( cp.PROJECT_COUNT )
	FROM
	( SELECT CLIENT_ID, COUNT( project.ID ) AS PROJECT_COUNT FROM project GROUP BY project.CLIENT_ID ) AS cp
	);