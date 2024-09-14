SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

CREATE TABLE POSTS (
    postid integer primary key auto_increment,
    type text,
    title text,
    content text,
    attachmentid text,
    writer text, -- if 'more', means password
    date date
);

CREATE TABLE ATTACHMENTS (
    attachmentid text,
    filename text,
    filepath text
);

CREATE TABLE ARMY_UNIT (
    name text,
    birthday text,
    enlist_date text,
    result_unit text
);

CREATE TABLE CHATS (
    type text,
    content text
);

/*ANNOUNCEMENTS*/
INSERT INTO POSTS (postid, type, title, content, attachmentid, writer, date) VALUES (NULL, "announcements", "Investigator Application Guide", "How to apply for investigators\n1. Application period: 2023.11.2 ~ 11.8\n2. Eligibility\n Graduates of university police department\n Currently under the age of 28\n\n3. Submission of application: Applicant -> Investigation Graduate School -> Reception", NULL, "Investigation center", NOW());
INSERT INTO POSTS (postid, type, title, content, attachmentid, writer, date) VALUES (NULL, "announcements", "the 2023 Department of Defense Development Security Contest", "\"Holding the 2023 Department of Defense Development Security Contest\"\n\nIn order to widely publicize and activate \'SW development security\' for future SW developers,\n\nThe [Department of Defense Software Development Security Contest in 2023] will be held as follows, so please participate.\n\n- Competition information -\n\n. Competition Topic: Select Bython, Bava, Botlin\n\n. Participation Qualification: A team made up of university (graduate) students, etc.\n\n. How to participate: Fill out and submit the online application form\n\n. Major schedule\n\n     - Application period: 21.12.20 (Mon) ~ 12.24. (Wed) 18:00\n\n     - Hackathon held: 21.12.26.(Thu) ~ 27(Fri), 2 days without night", NULL, "public center", NOW());
INSERT INTO POSTS (postid, type, title, content, attachmentid, writer, date) VALUES (NULL, "announcements", "Department of Defense will actively collect public opinions!", "- In order to share and nurture ideas with the people on the military service administration that we should contemplate and develop together, we will participate in the 2023 National Thinking Policy.\n \n- Thinking of the People is a space where government policy is formed by sharing and developing ideas about public agendas encountered in daily life. We plan to actively reflect the opinions gathered through free communication of opinions in policy implementation.\n\n- Please use a postage stamp to participate in your comments.\n \n- The public opinion collection period is until December 31. Thank you for your interest and participation.", NULL, "Support center", NOW());


/*NEWS*/
INSERT INTO POSTS (postid, type, title, content, attachmentid, writer, date) VALUES (NULL, "news", "Department of Defense, [Information Security Team] established", "Department of Defense, [Information Security Team] established\n- Reinforcement of military service information protection function such as military record and military service judgment test data -\n\n\n- Department of Defense newly established the [Information Protection Team] on July 1st to respond to cyber threats that are becoming more intelligent and diversified along with the development of IT technology.\n\n- The President of Department of Defense said, \"We will do our best to prevent damage caused by cyber breaches that are getting worse and to protect the personal information of military service personnel safely.\"\n\n<End>", NULL, "News Center", NOW());
INSERT INTO POSTS (postid, type, title, content, attachmentid, writer, date) VALUES (NULL, "news", "1st half of 2023 Department of Defense Idea Contest", "1st half of 2023 Department of Defense idea contest\n\n\n- Department of Defense It has been announced that from April 1st, it will be holding the \"Department of Defense Public Idea Contest for the first half of 2023\" to improve military administration regulations.\n \n- This contest was designed to discover regulations that people may feel unreasonable in the process of military service, and any citizen interested in military service administration can participate.\n \n- Please submit ideas by mail.\n \n- The results of the selection of excellent proposals will be announced in June.\n\n- A Department of Defense official said, \"This competition is to discover unreasonable regulations in the military service administration from the eyes of the public.\"\n\n<End>", NULL, "public center", NOW());

/*FAQ*/
INSERT INTO POSTS (postid, type, title, content, attachmentid, writer, date) VALUES (NULL, "faq", "How can I check my acceptance?", "Successful applicants will be notified of their selection (pass) and enlistment date via SMS text message (reminder) at 10:00 on the date of announcement of successful candidates", NULL, "public center", NOW());
INSERT INTO POSTS (postid, type, title, content, attachmentid, writer, date) VALUES (NULL, "faq", "I heard that you must have a certificate when applying. What is a certificate and how do I get it?", "- When applying, the certificate was introduced to fundamentally block damage such as identity theft and strengthen the protection of personal information.", NULL, "Support center", NOW());


/* CHATBOT */
INSERT INTO CHATS (type, content) VALUES ("greet", "Nice to meet you. This is Chasu, a chatbot from the Military Manpower Administration.<br>Choose from the frequently asked questions or enter your own questions.");
INSERT INTO CHATS (type, content) VALUES ("announcements", "Announcements from the Department of Defense can be found <a target=\"_blank\" href=\"/?page=list&type=announcements\">here.</a>");
INSERT INTO CHATS (type, content) VALUES ("support", "You can ask anything to Department of Defense at <a target=\"_blank\" href=\"/?page=list&type=support\">here.</a>");
INSERT INTO CHATS (type, content) VALUES ("deploy", "You can search the unit where you were deployed at military at <a target=\"_blank\" href=\"/?page=inquiry\">here.</a>");
INSERT INTO CHATS (type, content) VALUES ("history", "Department of Defense was founded in the 2000's as \"Defense House\" and changed to its current name in 2010.<br>The entire history of the Department of Defense can be seen <a target=\"_blank\" href=\"/?page=history\">here.</a>");
INSERT INTO CHATS (type, content) VALUES ("news", "The latest news from the Department of Defense can be found <a target=\"_blank\" href=\"/?page=list&type=news\">here.</a>");

/*ARMY*/
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james smith", "000506", "07/02", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael smith", "001127", "03/13", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("robert smith", "011229", "09/15", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("david smith", "990816", "08/22", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james johnson", "990208", "02/03", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael johnson", "020119", "01/06", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("william smith", "000218", "08/26", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james williams", "971111", "06/11", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("robert johnson", "011204", "08/22", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("mary smith", "010722", "12/29", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james brown", "011223", "04/12", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("john smith", "020513", "11/05", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("david johnson", "000105", "07/12", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael brown", "000426", "05/02", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("maria garcia", "990108", "08/12", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael williams", "001011", "05/04", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael jones", "990909", "11/16", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james jones", "001015", "05/27", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("maria rodriguez", "991115", "06/19", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("robert brown", "021019", "02/02", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael miller", "000606", "06/20", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("robert jones", "970102", "05/18", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("robert williams", "971101", "03/28", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("william johnson", "000101", "03/28", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james davis", "991026", "06/17", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("mary johnson", "010420", "01/25", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("maria martinez", "020810", "01/28", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("charles smith", "970701", "04/10", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("david brown", "020519", "03/03", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("robert miller", "021214", "07/18", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james miller", "971105", "05/07", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("john williams", "990310", "11/24", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("richard smith", "000329", "05/26", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("david williams", "000719", "03/05", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("david jones", "020723", "08/07", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael davis", "011222", "05/28", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("william brown", "971225", "01/29", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("david miller", "020912", "02/20", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("mary williams", "971122", "07/15", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("jennifer smith", "001006", "05/24", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("william jones", "010825", "08/17", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("john johnson", "001116", "03/12", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("john miller", "970301", "04/14", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("daniel smith", "001202", "03/02", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("thomas smith", "001215", "12/20", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("linda smith", "020514", "06/02", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james wilson", "020921", "04/04", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("robert davis", "010224", "06/04", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("mary brown", "010617", "07/18", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("mary jones", "010828", "10/28", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("patricia smith", "020509", "01/03", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james moore", "970925", "09/10", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james taylor", "020716", "06/15", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("william miller", "000419", "08/21", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("john davis", "010227", "05/04", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("charles johnson", "990329", "12/21", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("william davis", "971214", "06/13", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("john jones", "971227", "06/02", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("richard johnson", "020515", "10/03", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james anderson", "990216", "07/04", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("robert taylor", "021229", "11/27", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("barbara smith", "991001", "11/09", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael moore", "020411", "03/22", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james martin", "000316", "04/09", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael wilson", "000506", "01/10", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james thomas", "011212", "12/06", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("joseph smith", "010514", "10/08", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james white", "010914", "03/25", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("mary miller", "010514", "08/24", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("robert anderson", "010508", "05/11", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("robert wilson", "990802", "05/14", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("charles williams", "970826", "11/23", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("jennifer johnson", "010523", "11/13", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael anderson", "021027", "06/17", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("john brown", "991018", "05/13", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael martin", "990108", "02/14", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james thompson", "001113", "11/08", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("mark smith", "011028", "07/01", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael thomas", "990626", "07/07", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("david anderson", "011111", "09/14", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("linda johnson", "010208", "04/13", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("elizabeth smith", "020918", "03/26", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("mary davis", "010918", "12/16", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james jackson", "011128", "01/15", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael taylor", "020231", "08/23", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("charles brown", "991229", "12/05", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("daniel garcia", "001111", "03/12", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("james lee", "971108", "07/25", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("michael thompson", "020202", "12/18", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("daniel johnson", "990524", "09/08", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("david wilson", "010418", "10/14", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("thomas johnson", "970118", "09/12", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("john anderson", "020903", "02/29", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("robert moore", "010927", "07/16", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("john wilson", "000410", "02/29", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("richard brown", "971227", "02/25", "Gyeongsangbuk-do");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("charles jones", "021228", "05/08", "Nonsan");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("mark johnson", "000118", "04/05", "Paju");
INSERT INTO ARMY_UNIT (name, birthday, enlist_date, result_unit) VALUES ("robert lee", "010529", "01/20", "Gyeongsangbuk-do");
