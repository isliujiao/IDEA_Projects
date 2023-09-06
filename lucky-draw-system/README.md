## 项目介绍
抽奖活动 （模拟1千同时去抽奖，奖项信息使用抽奖配置.抽出奖品上限为配置里lotteryNumber指定的数量）
## 运行说明：
发送POST请求： "http://localhost:8889/luckyDraw/lottery"
携带body为JSON格式：
```
{
	"lotteryNumber": 60,
	"awards": [{
			"id": 1,
			"describe": "一等奖",
			"count": 10
		},
		{
			"id": 2,
			"describe": "二等奖",
			"count": 20
		},
		{
			"id": 3,
			"describe": "三等奖",
			"count": 30
		}
	]
}
```

```text
说明：需要设置奖池相关配置信息
lotteryNumber：奖池总数
awards：每个奖项的说明和数量
```

返回结果：
List ——> 返回获奖名单，包括<b>获奖人姓名+奖项等级说明</b>（获奖人姓名用UUID随机生成）

### 数据库访问
```
数据库相关信息见：luck_draw.sql 直接执行包括数据库结构

lottery_config：奖品总量配置奖
award表：奖项表
winner：获奖人员表
```


