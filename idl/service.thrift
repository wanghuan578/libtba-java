
include "common.thrift"
namespace java com.spirit.essential.rpc.protocol.thrift
//namespace cpp essential.provider

enum QualityMessageType
//enum MessageType
{   
	MT_SERVICE_QUALITY_SYNC 		= 1500,
	MT_SERVICE_QUALITY_RES,
}

struct ApplicationInfo
{
	1:i32						pid, //进程id
	2:i32						ppid,//父进程id
	3:i32						upstream_flow_total,		//上行流量（总）
	4:i32						upstream_flow_bytes,		//上行流量（每秒）
	5:i32						downstream_flow_total,		//下行流量（总）
	6:i32						downstream_flow_bytes,		//下行流量（每秒）
	7:i32						current_connections,		//当前连接数
	8:i32						max_connections,			//最大连接数
}

struct SystemInfo
{
	1:i32				task_num,				//进程数量
	2:i16				cpu_num,				//cpu数量
	3:double			cpu_percent,			//cpu使用率
	4:i32 				mem_total,				//总内存
	5:i32 				mem_used,				//使用内存
	6:i64				mem_avail,				//可用内存
	7:double			mem_percent,			//内存使用率
	8:i32				swap_total,				//交换区大小
	9:i32				swap_used,				//交换区使用
	10:i32				swap_avail,				//交换区可用
	11:double			swap_percent,			//交换区使用率
	12:double 			load_average,			//系统负载
}

struct ServiceInfo
{
	1:ApplicationInfo 			app,
	2:SystemInfo 				system,
	3:common.RouteInfo      	route,
}