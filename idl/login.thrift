
include "common.thrift"
namespace cpp essential.login
//namespace java com.spirit.essential.rpc.protocol.thrift

//enum LoginMessageType
enum MessageType
{   
    MT_CLIENT_PASSWORD_LOGIN_REQ = 500,
    MT_CLIENT_LOGIN_RES,
    MT_CLIENT_LOGOUT_REQ,
    MT_CLIENT_LOGOUT_RES,
}

struct ClientPasswordLoginReq
{
    1:i64    	user_id,                        //��¼��
    2:i64       client_random,                  //�ͻ��������
    3:string    client_mac,                     //�ͻ���mac
    4:string    client_version,                 //�ͻ��˰汾��
    5:string    check_sum,                       //�����( hex(md5(ClientPasswordLoginReqChecksum���л�)) )
}

struct ClientPasswordLoginReqChecksum
{
    1:i64    	user_id,                        //��¼��
    2:string    password,                       //hex(md5(�û���������))
    3:i64       client_random,                  //�ͻ��������
    4:i64       server_random,                  //����������
}

struct ClientLoginRes
{
    1:i32       error_code,
    2:string    error_text,
    3:string    session_ticket,                 //�ỰƱ��
}

struct LogoutReq
{
    1:i64       user_id,                        //�û�id
    2:string    session_ticket,                 //�ỰƱ��
}

struct LogoutRes
{
    1:i32       error_code,
    2:i64       user_id,
}