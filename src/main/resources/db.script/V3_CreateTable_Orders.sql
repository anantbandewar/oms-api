CREATE TABLE [dbo].[Orders]
(
	[OrderId]		BIGINT IDENTITY(1, 1),
	[UserId]    	BIGINT NOT NULL,
	[AddressId] 	BIGINT NOT NULL,
	[Status]    	VARCHAR(25) NOT NULL,
	[DueDate]       DATETIME NOT NULL,
	[Total]         DECIMAL(18, 2) NOT NULL,
	[UserCreated]   BIGINT NOT NULL,
	[DateCreated]   DATETIME NOT NULL,
	[UserModified]  BIGINT,
    [DateModified]  DATETIME,

	CONSTRAINT [PK_Orders] PRIMARY KEY ([OrderId]),
	CONSTRAINT [FK_Orders_User] FOREIGN KEY ([UserId]) REFERENCES [dbo].[User]([UserId]),
	CONSTRAINT [FK_Orders_Address] FOREIGN KEY ([AddressId]) REFERENCES [dbo].[ShippingAddress]([AddressId])
);
GO
