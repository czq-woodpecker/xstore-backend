# Tasking

1. given name(牛奶)、price(3.2)、unit（瓶） and image url, when submit request of create product, then create the product and return 201 status.
2. given price(abc)、unit(瓶) and image url，when submit request of create product，then return 400 status.
3. given already exist name、price(3.3)、unit(瓶) and imageUrl when submit request of create product， then return 400 and error message 商品名称已存在，请输入新的商品名称。

1. given added two products, when get products, then return a list containing the two products.
2. given nothing, when get products, then return a list containing no value.

1. given add product(id:1), when add order with productId 1, then return 201 status.
2. given nothing, when add order with productId 1, then return 400 status and error message not exist productId: 1.

1. given nothing, when getOrders, then return 200 status and a empty list.
2. given add two orders, when getOrders, then return 200 status and a list containing the two orders.

1. given nothing, when deleteOrder by orderId 1, then return 400 status and error message not exist orderId: 1.
2. given add a order, when deleteOrder by orderId1, then return 200 status.