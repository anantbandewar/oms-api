CREATE TABLE [dbo].[ShippingAddress]
(
	[AddressId]		BIGINT IDENTITY(1, 1),
	[UserId]        BIGINT NOT NULL,
	[Type]			VARCHAR(10) NOT NULL,
	[AddressLine1]	VARCHAR(25) NOT NULL,
	[AddressLine2]	VARCHAR(25) NULL,
	[City]  		VARCHAR(50) NOT NULL,
	[State]  		VARCHAR(50) NOT NULL,
	[Country]  		VARCHAR(50) NOT NULL,
	[PostalCode]	VARCHAR(10) NOT NULL,

	CONSTRAINT [PK_ShippingAddress] PRIMARY KEY ([AddressId]),
	CONSTRAINT [FK_ShippingAddress_User] FOREIGN KEY ([UserId]) REFERENCES [dbo].[User]([UserId])
);
GO
