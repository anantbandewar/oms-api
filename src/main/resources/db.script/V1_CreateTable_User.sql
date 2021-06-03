CREATE TABLE [dbo].[User]
(
	[UserId]    	BIGINT IDENTITY(1, 1),
	[FirstName]   	VARCHAR(50) NOT NULL,
	[LastName]   	VARCHAR(50) NOT NULL,
	[Mobile]    	VARCHAR(10) NOT NULL,
	[Email]         VARCHAR(100) NOT NULL,

	CONSTRAINT [PK_User] PRIMARY KEY ([UserId])
);
GO
