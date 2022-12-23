# AOP

1、https://www.bilibili.com/video/BV1G54y1V7VG?p=39&vd_source=a718590390071ba1d900a1c8d29000c9

2、尚硅谷权限管理项目、BLOG项目中AOP相关代码

# 库存超买超卖

* 库存为负

```java
        boolean seckillGoodsResult = itSeckillGoodsService.update(new UpdateWrapper<TSeckillGoods>()
                .setSql("stock_count = " + "stock_count-1")
                .eq("goods_id", goodsVo.getId())
                .gt("stock_count", 0)
        );
```



* 库存正常，订单过多

下述代码会出现该情况

```java
        TSeckillGoods seckillGoods = itSeckillGoodsService.getOne(new QueryWrapper<TSeckillGoods>().eq("goods_id", goodsVo.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
       itSeckillGoodsService.updateById(seckillGoods);
       boolean seckillGoodsResult = itSeckillGoodsService.update(new UpdateWrapper<TSeckillGoods>()
               .set("stock_count", seckillGoods.getStockCount())
               .eq("id", seckillGoods.getId())
               .gt("stock_count", 0)
       );
```

改为

```java
        boolean seckillGoodsResult = itSeckillGoodsService.update(new UpdateWrapper<TSeckillGoods>()
                .setSql("stock_count = " + "stock_count-1")
                .eq("goods_id", goodsVo.getId())
                .gt("stock_count", 0)
        );
```

这可以看成是采用乐观锁的形式实现

* 一个用户购买多个商品

1、构建唯一索引插入数据防止重复

```sql
ALTER TABLE `seckill`.`t_seckill_order` 
ADD UNIQUE INDEX `seckill_uid_gid`(user_id, goods_id) USING BTREE COMMENT '用户ID+商品ID成为唯一索引，';
```

2、使用redis快速查询订单是否重复

```sql
redisTemplate.opsForValue().set("order:" + user.getId() + ":" + goodsVo.getId(), tSeckillOrder);

TSeckillOrder tSeckillOrder = (TSeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        if (tSeckillOrder != null) {
            return RespBean.error(RespBeanEnum.REPEATE_ERROR);
        }
```



参考链接：

[1] [[面试题]商品超买超卖问题分析以及实战](https://blog.csdn.net/qq_46514118/article/details/123640456)
[2] 