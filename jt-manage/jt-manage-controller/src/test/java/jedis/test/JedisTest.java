package jedis.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class JedisTest {
	
	@Test
	public void jedis() throws Exception
	{
		//启动redis服务器  redis-server &
		//先打开端口/sbin/iptables -I INPUT -p tcp --dport 6379 -j ACCEPT
		//登录进去redis-cli -p 6379
		//设置模式config set protected-mode "no"
		//关闭服务  shutdown
		Jedis jedis = new Jedis("192.168.109.129", 6380);
//		String encoderByMd5 = MD5Util.EncoderByMd5("asd3135");
//		System.out.println(encoderByMd5.toUpperCase());
		//698E648F914C299BA3C578936484AB6B
//		jedis.auth("asd3135");
		jedis.select(1);
		/*Set<String> keys = jedis.keys("*");
		Iterator<String> iterator = keys.iterator();
		Map<String, String> hashMap = new HashMap<String,String>();
		while(iterator.hasNext())
		{
			String key = iterator.next();
			String value = jedis.get(key);
			hashMap.put(key, value);
		}
		String json = JSONObject.toJSON(hashMap).toString();*/
		jedis.select(1);
		String username = jedis.get("name");
		String password = jedis.get("pass");
		System.out.println(username);
		System.out.println(password);
		jedis.close();
	}
	
	@Test
	public void shard()
	{
		
		List<JedisShardInfo> shards=new  ArrayList<JedisShardInfo>();
		JedisShardInfo jedis1 = new JedisShardInfo("192.168.109.129",6380);
		shards.add(jedis1);
		JedisShardInfo jedis2 = new JedisShardInfo("192.168.109.129",6381);
		shards.add(jedis2);
		//构建分片jedis对象
		ShardedJedis shardedJedis = new ShardedJedis(shards);
		shardedJedis.set("test", "123");
		shardedJedis.close();
	}
	
	@Test
	public void sentinel(){
		//多个哨兵之间没有监控关系，顺序遍历，哪个能通就走哪个，不通，再检查下一个哨兵
    	Set<String> sentinels = new HashSet<String>();
    	sentinels.add(new HostAndPort("192.168.109.129",26379).toString());
    	
    	//mymaster是在sentinel.conf中配置的名称，要把配置文件中下面的IP地址修改成真实的IP地址
    	//sentinel monitor mymaster 192.168.163.200 6380 1，默认30秒后开始选举
    	JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
    	System.out.println("当前master：" + pool.getCurrentHostMaster());
    	
    	Jedis jedis = pool.getResource();
//		jedis.auth("asd3135");

    	System.out.println(jedis.get("name"));
    	pool.returnResource(jedis);   
    	
    	pool.destroy();
    	System.out.println("ok");
	}
	
}
