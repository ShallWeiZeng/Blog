public class JedisSource{

    private static JedisPool jedisPool = null;

    private int maxIdle = 10;
    public void setMaxIdle(int maxIdle){
        this.maxIdle = maxIdle;
    }

    private int maxTotal= 50;
    public void setMaxTotal(int maxTotal){
        this.maxTotal = maxTotal;
    }

    private long maxWaitMills = 5000;
    public void setMaxWaitMills(long maxWaitMills){
        this.maxWaitMills = maxWaitMills;
    }

    private String host="localhost";
    public void setHost(String host){
        this.host = host;
    }

    private int port = 6379;
    public void setPort(int port){
        this.port = port;
    }

    private int timeOut=18000;
    public void setTimeOut(int timeOut){
        this.timeOut = timeOut;
    }

    private String password  ="";
    public void setPassword(String password){
        this.password=password;
    }
    public void initialPool(){
        try{

            final JedisPoolConfig config=new JedisPoolConfig();
            config.setMaxTotal(maxTotal);
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMills(maxWaitMills);
            config.setTestOnBorrow(false);

            jedisPool = new JedisPool(config,host,port,timeOut,password);

            System.out.println("************************************");
            System.out.println("JedisPool is created :host:"+host+" port:"+port+" password:"+password);
            System.out.println("************************************");


        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }
    }
    public sysnchronized Jedis getJedisInstance(final int dbIndex){
        try{
            if(jedisPool != null){
                Jedis resource = jedisPool.getResource();
                resource.select(dbIndex);
                return resource;
            }
            else{
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void return returnResource(final Jedis jedis){
        if(jedis != null){
            jedisPool.returnResource(redis);
        }
    }
}

/*
<bean id="jedisSource" class = "JedisSource" init-method="initialPool">
    <property name="host" value="IPAddress"></property>
    <property name="port" value="6379"></property>
    <property name="password" value="123"></property>
    <property name="maxTotal" value="300"></property>
    <property name="maxIdle" value="3"></property>
    <property name="maxWaitMills" value="100000"></property>
</bean>
*/
