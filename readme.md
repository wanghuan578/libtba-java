

## 基于thrift二进制协议的java序列化/反序列化工具


### 一键安装

- mvn clean install



### 采用框架及功能

- 实现了类的序列化和反序列化


### 特性

- 利用thrift 的idl工具，可方便的生成多种跨平台协议。


### IDL工具使用

- 生成的文件就是协议。

#### C++命令
- ./thrift --gen cpp common.thrift 

#### JAVA命令
- ./thrift --gen java login_server.thrift


### 协议格式

#### head

- length（协议长度）和type（协议类型）是关键字段。


```
public class TsRpcHead {

	public static final int HEAD_SIZE = 40;
	
	private int    	length;            
	private short  	flag;              
	private short  	type;               
	private int    	sequence;          
	private int    	source;            
	private int    	destination;       
	private int     checksum;         
	private int     attach_id1;       
	private int     attach_id2;     
	private int     attach_id3;        
	private int     attach_id4;
	}
```

#### body

- idl生成的协议类。



### 演示样例


#### 序列化(encode)


```
	byte[] msg = TbaUtil<ServiceInfo>().Serialize(clazz, 1024);
```


#### 反序列化(decode)


```
	ServiceInfo clazz = new TbaUtil<ServiceInfo>().Deserialize(msg, ServiceInfo.class);
```


#### 网络字节序列化(encode)

```
	void channelRead(ChannelHandlerContext ctx, Object msg) {

        if (msg instanceof ClientPasswordLoginReq) {

            log.info("ClientPasswordLoginReq: {}", JSON.toJSONString(msg, true));

            ClientPasswordLoginReq entity = (ClientPasswordLoginReq) msg;

            //todo

            ClientLoginRes body = new ClientLoginRes();
            body.error_code = 0;
            body.error_text = "OK";
            body.session_ticket = "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW";

            TsRpcHead head = new TsRpcHead(RpcEventType.MT_CLIENT_LOGIN_RES);
            ctx.write(new TsEvent(head, body, 1024));
            ctx.flush();
        }
		
		...
		
		
	}
```	

```	
	public class ThriftBinaryProtocolEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

		TsEvent ev = (TsEvent) msg;

		try {
			TsRpcHead head = ev.getHead();
			TsRpcProtocolFactory protocol = new TsRpcProtocolFactory<TBase>((TBase)ev.getBody(), head, ev.getLen());
			byte[] buf = protocol.Encode().OutStream().GetBytes();
			log.info("encode msg len: {}", buf.length);
			out.writeBytes(buf, 0, buf.length);
		}
		catch (TbaException e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}

}
```

#### 网络字节反序列化(decode)

```
public class ThriftBinaryProtocolDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // TODO Auto-generated method stub

        while (in.readableBytes() > 4) {
            
            int msg_len = in.readInt();
            TsRpcByteBuffer msg = new TsRpcByteBuffer(msg_len);
            msg.WriteI32(msg_len);

            for (int i = 0; i < msg_len - 4; i++) {
                msg.WriteByte(in.readByte());
            }

            TsRpcEventParser parser = new TsRpcEventParser(msg);
            TsRpcHead header = parser.Head();

            log.info("Msg Type: {}", header.GetType());

            try {
                switch (header.GetType()) {

                    case RpcEventType.MT_CLIENT_PASSWORD_LOGIN_REQ: {
                        TsRpcProtocolFactory<ClientPasswordLoginReq> protocol = new TsRpcProtocolFactory<ClientPasswordLoginReq>(msg);
                        out.add(protocol.Decode(ClientPasswordLoginReq.class));
                    }
                        break;


			...
	}
```




### 相关资源

- [c++版序列化工具](https://github.com/wanghuan578/libtba-cc)(libtba-cc)。
- [框架相关应用](https://github.com/wanghuan578/essential)(essential)。


### 作者和贡献者信息


- spirit(57810140@qq.com)

