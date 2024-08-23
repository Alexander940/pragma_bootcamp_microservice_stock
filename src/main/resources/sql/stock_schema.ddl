CREATE TABLE Category (UniqueID int(10) NOT NULL AUTO_INCREMENT, name varchar(50) UNIQUE, description varchar(90) NOT NULL, PRIMARY KEY (UniqueID));
CREATE TABLE Article (UniqueID int(10) NOT NULL AUTO_INCREMENT, name varchar(255), description text, quantity int(10), price int(10), BrandUniqueID int(10) NOT NULL, PRIMARY KEY (UniqueID));
CREATE TABLE Article_Category (UniqueID int(10) NOT NULL AUTO_INCREMENT, ArticleUniqueID int(10) NOT NULL, CategoryUniqueID int(10) NOT NULL, PRIMARY KEY (UniqueID));
CREATE TABLE Brand (UniqueID int(10) NOT NULL AUTO_INCREMENT, name varchar(50) UNIQUE, description varchar(120) NOT NULL, PRIMARY KEY (UniqueID));
ALTER TABLE Article_Category ADD CONSTRAINT FKArticle_Ca353379 FOREIGN KEY (ArticleUniqueID) REFERENCES Article (UniqueID);
ALTER TABLE Article_Category ADD CONSTRAINT FKArticle_Ca756346 FOREIGN KEY (CategoryUniqueID) REFERENCES Category (UniqueID);
ALTER TABLE Article ADD CONSTRAINT FKArticle797117 FOREIGN KEY (BrandUniqueID) REFERENCES Brand (UniqueID);
