CREATE TABLE [dbo].[Product]
(
	[ProductId]	    BIGINT IDENTITY(1, 1),
	[Name]         	VARCHAR(50) NOT NULL,
    [Height]		DECIMAL(18, 2) NOT NULL,
    [Weight]        DECIMAL(18, 2) NOT NULL,
	[Price]         DECIMAL(18, 2) NOT NULL,
	[ImageUrl] 	    VARCHAR(100),
	[Quantity]    	FLOAT NOT NULL,
	[Unit]          VARCHAR(25) NOT NULL,
	[Barcode]       VARCHAR(50) NOT NULL,

	CONSTRAINT [PK_Product] PRIMARY KEY ([ProductId])
);
GO
