/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
#ifndef common_TYPES_H
#define common_TYPES_H

#include <iosfwd>

#include <thrift/Thrift.h>
#include <thrift/TApplicationException.h>
#include <thrift/TBase.h>
#include <thrift/protocol/TProtocol.h>
#include <thrift/transport/TTransport.h>

#include <thrift/cxxfunctional.h>


namespace essential { namespace common {

struct MessageType {
  enum type {
    MT_HELLO_NOTIFY = 100,
    MT_HELLO_RES = 101,
    MT_KEEPALIVE_REQ = 102,
    MT_KEEPALIVE_RES = 103
  };
};

extern const std::map<int, const char*> _MessageType_VALUES_TO_NAMES;

struct ErrorCode {
  enum type {
    OK = 0,
    LOGIN_FAILED = 100,
    LOGNAME_EXIST = 101,
    LOGNAME_NOT_EXIST = 102,
    PASSWORD_ERROR = 103,
    SERVICE_REGISTER_FAILED = 104
  };
};

extern const std::map<int, const char*> _ErrorCode_VALUES_TO_NAMES;

class AddressInfo;

class RouteInfo;

class HelloNotify;

class SessionTicket;

class CommonNull;

class CommonRes;

class KeepAliveReq;

class KeepAliveRes;

typedef struct _AddressInfo__isset {
  _AddressInfo__isset() : ip(false), port(false) {}
  bool ip :1;
  bool port :1;
} _AddressInfo__isset;

class AddressInfo : public virtual ::apache::thrift::TBase {
 public:

  AddressInfo(const AddressInfo&);
  AddressInfo& operator=(const AddressInfo&);
  AddressInfo() : ip(), port(0) {
  }

  virtual ~AddressInfo() throw();
  std::string ip;
  int16_t port;

  _AddressInfo__isset __isset;

  void __set_ip(const std::string& val);

  void __set_port(const int16_t val);

  bool operator == (const AddressInfo & rhs) const
  {
    if (!(ip == rhs.ip))
      return false;
    if (!(port == rhs.port))
      return false;
    return true;
  }
  bool operator != (const AddressInfo &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const AddressInfo & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  virtual void printTo(std::ostream& out) const;
};

void swap(AddressInfo &a, AddressInfo &b);

inline std::ostream& operator<<(std::ostream& out, const AddressInfo& obj)
{
  obj.printTo(out);
  return out;
}

typedef struct _RouteInfo__isset {
  _RouteInfo__isset() : id(false), name(false), weight(false), address(false) {}
  bool id :1;
  bool name :1;
  bool weight :1;
  bool address :1;
} _RouteInfo__isset;

class RouteInfo : public virtual ::apache::thrift::TBase {
 public:

  RouteInfo(const RouteInfo&);
  RouteInfo& operator=(const RouteInfo&);
  RouteInfo() : id(0), name(), weight(0) {
  }

  virtual ~RouteInfo() throw();
  int32_t id;
  std::string name;
  int16_t weight;
  AddressInfo address;

  _RouteInfo__isset __isset;

  void __set_id(const int32_t val);

  void __set_name(const std::string& val);

  void __set_weight(const int16_t val);

  void __set_address(const AddressInfo& val);

  bool operator == (const RouteInfo & rhs) const
  {
    if (!(id == rhs.id))
      return false;
    if (!(name == rhs.name))
      return false;
    if (!(weight == rhs.weight))
      return false;
    if (!(address == rhs.address))
      return false;
    return true;
  }
  bool operator != (const RouteInfo &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const RouteInfo & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  virtual void printTo(std::ostream& out) const;
};

void swap(RouteInfo &a, RouteInfo &b);

inline std::ostream& operator<<(std::ostream& out, const RouteInfo& obj)
{
  obj.printTo(out);
  return out;
}

typedef struct _HelloNotify__isset {
  _HelloNotify__isset() : error_code(false), error_text(false), service_name(false), service_id(false), server_random(false), server_time(false) {}
  bool error_code :1;
  bool error_text :1;
  bool service_name :1;
  bool service_id :1;
  bool server_random :1;
  bool server_time :1;
} _HelloNotify__isset;

class HelloNotify : public virtual ::apache::thrift::TBase {
 public:

  HelloNotify(const HelloNotify&);
  HelloNotify& operator=(const HelloNotify&);
  HelloNotify() : error_code(0), error_text(), service_name(), service_id(0), server_random(0), server_time(0) {
  }

  virtual ~HelloNotify() throw();
  int32_t error_code;
  std::string error_text;
  std::string service_name;
  int32_t service_id;
  int64_t server_random;
  int64_t server_time;

  _HelloNotify__isset __isset;

  void __set_error_code(const int32_t val);

  void __set_error_text(const std::string& val);

  void __set_service_name(const std::string& val);

  void __set_service_id(const int32_t val);

  void __set_server_random(const int64_t val);

  void __set_server_time(const int64_t val);

  bool operator == (const HelloNotify & rhs) const
  {
    if (!(error_code == rhs.error_code))
      return false;
    if (!(error_text == rhs.error_text))
      return false;
    if (!(service_name == rhs.service_name))
      return false;
    if (!(service_id == rhs.service_id))
      return false;
    if (!(server_random == rhs.server_random))
      return false;
    if (!(server_time == rhs.server_time))
      return false;
    return true;
  }
  bool operator != (const HelloNotify &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const HelloNotify & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  virtual void printTo(std::ostream& out) const;
};

void swap(HelloNotify &a, HelloNotify &b);

inline std::ostream& operator<<(std::ostream& out, const HelloNotify& obj)
{
  obj.printTo(out);
  return out;
}

typedef struct _SessionTicket__isset {
  _SessionTicket__isset() : user_id(false), user_name(false), email(false), nick_name(false), client_ip(false), client_mac(false), client_version(false), app_id(false), session_id(false), session_key(false), create_time(false), end_time(false) {}
  bool user_id :1;
  bool user_name :1;
  bool email :1;
  bool nick_name :1;
  bool client_ip :1;
  bool client_mac :1;
  bool client_version :1;
  bool app_id :1;
  bool session_id :1;
  bool session_key :1;
  bool create_time :1;
  bool end_time :1;
} _SessionTicket__isset;

class SessionTicket : public virtual ::apache::thrift::TBase {
 public:

  SessionTicket(const SessionTicket&);
  SessionTicket& operator=(const SessionTicket&);
  SessionTicket() : user_id(0), user_name(), email(), nick_name(), client_ip(), client_mac(), client_version(), app_id(0), session_id(), session_key(), create_time(0), end_time(0) {
  }

  virtual ~SessionTicket() throw();
  int32_t user_id;
  std::string user_name;
  std::string email;
  std::string nick_name;
  std::string client_ip;
  std::string client_mac;
  std::string client_version;
  int32_t app_id;
  std::string session_id;
  std::string session_key;
  int64_t create_time;
  int64_t end_time;

  _SessionTicket__isset __isset;

  void __set_user_id(const int32_t val);

  void __set_user_name(const std::string& val);

  void __set_email(const std::string& val);

  void __set_nick_name(const std::string& val);

  void __set_client_ip(const std::string& val);

  void __set_client_mac(const std::string& val);

  void __set_client_version(const std::string& val);

  void __set_app_id(const int32_t val);

  void __set_session_id(const std::string& val);

  void __set_session_key(const std::string& val);

  void __set_create_time(const int64_t val);

  void __set_end_time(const int64_t val);

  bool operator == (const SessionTicket & rhs) const
  {
    if (!(user_id == rhs.user_id))
      return false;
    if (!(user_name == rhs.user_name))
      return false;
    if (!(email == rhs.email))
      return false;
    if (!(nick_name == rhs.nick_name))
      return false;
    if (!(client_ip == rhs.client_ip))
      return false;
    if (!(client_mac == rhs.client_mac))
      return false;
    if (!(client_version == rhs.client_version))
      return false;
    if (!(app_id == rhs.app_id))
      return false;
    if (!(session_id == rhs.session_id))
      return false;
    if (!(session_key == rhs.session_key))
      return false;
    if (!(create_time == rhs.create_time))
      return false;
    if (!(end_time == rhs.end_time))
      return false;
    return true;
  }
  bool operator != (const SessionTicket &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const SessionTicket & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  virtual void printTo(std::ostream& out) const;
};

void swap(SessionTicket &a, SessionTicket &b);

inline std::ostream& operator<<(std::ostream& out, const SessionTicket& obj)
{
  obj.printTo(out);
  return out;
}


class CommonNull : public virtual ::apache::thrift::TBase {
 public:

  CommonNull(const CommonNull&);
  CommonNull& operator=(const CommonNull&);
  CommonNull() {
  }

  virtual ~CommonNull() throw();

  bool operator == (const CommonNull & /* rhs */) const
  {
    return true;
  }
  bool operator != (const CommonNull &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const CommonNull & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  virtual void printTo(std::ostream& out) const;
};

void swap(CommonNull &a, CommonNull &b);

inline std::ostream& operator<<(std::ostream& out, const CommonNull& obj)
{
  obj.printTo(out);
  return out;
}

typedef struct _CommonRes__isset {
  _CommonRes__isset() : error_code(false), error_text(false) {}
  bool error_code :1;
  bool error_text :1;
} _CommonRes__isset;

class CommonRes : public virtual ::apache::thrift::TBase {
 public:

  CommonRes(const CommonRes&);
  CommonRes& operator=(const CommonRes&);
  CommonRes() : error_code(0), error_text() {
  }

  virtual ~CommonRes() throw();
  int32_t error_code;
  std::string error_text;

  _CommonRes__isset __isset;

  void __set_error_code(const int32_t val);

  void __set_error_text(const std::string& val);

  bool operator == (const CommonRes & rhs) const
  {
    if (!(error_code == rhs.error_code))
      return false;
    if (!(error_text == rhs.error_text))
      return false;
    return true;
  }
  bool operator != (const CommonRes &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const CommonRes & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  virtual void printTo(std::ostream& out) const;
};

void swap(CommonRes &a, CommonRes &b);

inline std::ostream& operator<<(std::ostream& out, const CommonRes& obj)
{
  obj.printTo(out);
  return out;
}

typedef struct _KeepAliveReq__isset {
  _KeepAliveReq__isset() : id(false), timestamp_begin(false), serivce_quality(false) {}
  bool id :1;
  bool timestamp_begin :1;
  bool serivce_quality :1;
} _KeepAliveReq__isset;

class KeepAliveReq : public virtual ::apache::thrift::TBase {
 public:

  KeepAliveReq(const KeepAliveReq&);
  KeepAliveReq& operator=(const KeepAliveReq&);
  KeepAliveReq() : id(0), timestamp_begin(0), serivce_quality() {
  }

  virtual ~KeepAliveReq() throw();
  int32_t id;
  int64_t timestamp_begin;
  std::string serivce_quality;

  _KeepAliveReq__isset __isset;

  void __set_id(const int32_t val);

  void __set_timestamp_begin(const int64_t val);

  void __set_serivce_quality(const std::string& val);

  bool operator == (const KeepAliveReq & rhs) const
  {
    if (!(id == rhs.id))
      return false;
    if (!(timestamp_begin == rhs.timestamp_begin))
      return false;
    if (!(serivce_quality == rhs.serivce_quality))
      return false;
    return true;
  }
  bool operator != (const KeepAliveReq &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const KeepAliveReq & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  virtual void printTo(std::ostream& out) const;
};

void swap(KeepAliveReq &a, KeepAliveReq &b);

inline std::ostream& operator<<(std::ostream& out, const KeepAliveReq& obj)
{
  obj.printTo(out);
  return out;
}

typedef struct _KeepAliveRes__isset {
  _KeepAliveRes__isset() : error_code(false), error_text(false), id(false), timestamp_begin(false), timestamp_end(false) {}
  bool error_code :1;
  bool error_text :1;
  bool id :1;
  bool timestamp_begin :1;
  bool timestamp_end :1;
} _KeepAliveRes__isset;

class KeepAliveRes : public virtual ::apache::thrift::TBase {
 public:

  KeepAliveRes(const KeepAliveRes&);
  KeepAliveRes& operator=(const KeepAliveRes&);
  KeepAliveRes() : error_code(0), error_text(), id(0), timestamp_begin(0), timestamp_end(0) {
  }

  virtual ~KeepAliveRes() throw();
  int32_t error_code;
  std::string error_text;
  int32_t id;
  int64_t timestamp_begin;
  int64_t timestamp_end;

  _KeepAliveRes__isset __isset;

  void __set_error_code(const int32_t val);

  void __set_error_text(const std::string& val);

  void __set_id(const int32_t val);

  void __set_timestamp_begin(const int64_t val);

  void __set_timestamp_end(const int64_t val);

  bool operator == (const KeepAliveRes & rhs) const
  {
    if (!(error_code == rhs.error_code))
      return false;
    if (!(error_text == rhs.error_text))
      return false;
    if (!(id == rhs.id))
      return false;
    if (!(timestamp_begin == rhs.timestamp_begin))
      return false;
    if (!(timestamp_end == rhs.timestamp_end))
      return false;
    return true;
  }
  bool operator != (const KeepAliveRes &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const KeepAliveRes & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  virtual void printTo(std::ostream& out) const;
};

void swap(KeepAliveRes &a, KeepAliveRes &b);

inline std::ostream& operator<<(std::ostream& out, const KeepAliveRes& obj)
{
  obj.printTo(out);
  return out;
}

}} // namespace

#endif
