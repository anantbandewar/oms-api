CREATE TABLE [dbo].[OrderItem]
(
	[OrderItemId]	BIGINT IDENTITY(1, 1),
	[OrderId]   	BIGINT NOT NULL,
	[ProductId] 	BIGINT NOT NULL,
	[Quantity]    	FLOAT NOT NULL,
	[Unit]          VARCHAR(25) NOT NULL,

	CONSTRAINT [PK_OrderItem] PRIMARY KEY ([OrderItemId]),
	CONSTRAINT [FK_OrderItem_Order] FOREIGN KEY ([OrderId]) REFERENCES [dbo].[Orders]([OrderId]),
	CONSTRAINT [FK_OrderItem_Product] FOREIGN KEY ([ProductId]) REFERENCES [dbo].[Product]([ProductId])
);
GO
