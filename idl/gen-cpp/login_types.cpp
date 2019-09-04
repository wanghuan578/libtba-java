/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
#include "login_types.h"

#include <algorithm>
#include <ostream>

#include <thrift/TToString.h>

namespace essential { namespace login {

int _kMessageTypeValues[] = {
  MessageType::MT_CLIENT_PASSWORD_LOGIN_REQ,
  MessageType::MT_CLIENT_LOGIN_RES,
  MessageType::MT_CLIENT_LOGOUT_REQ,
  MessageType::MT_CLIENT_LOGOUT_RES
};
const char* _kMessageTypeNames[] = {
  "MT_CLIENT_PASSWORD_LOGIN_REQ",
  "MT_CLIENT_LOGIN_RES",
  "MT_CLIENT_LOGOUT_REQ",
  "MT_CLIENT_LOGOUT_RES"
};
const std::map<int, const char*> _MessageType_VALUES_TO_NAMES(::apache::thrift::TEnumIterator(4, _kMessageTypeValues, _kMessageTypeNames), ::apache::thrift::TEnumIterator(-1, NULL, NULL));


ClientPasswordLoginReq::~ClientPasswordLoginReq() throw() {
}


void ClientPasswordLoginReq::__set_user_id(const int64_t val) {
  this->user_id = val;
}

void ClientPasswordLoginReq::__set_client_random(const int64_t val) {
  this->client_random = val;
}

void ClientPasswordLoginReq::__set_client_mac(const std::string& val) {
  this->client_mac = val;
}

void ClientPasswordLoginReq::__set_client_version(const std::string& val) {
  this->client_version = val;
}

void ClientPasswordLoginReq::__set_check_sum(const std::string& val) {
  this->check_sum = val;
}

uint32_t ClientPasswordLoginReq::read(::apache::thrift::protocol::TProtocol* iprot) {

  apache::thrift::protocol::TInputRecursionTracker tracker(*iprot);
  uint32_t xfer = 0;
  std::string fname;
  ::apache::thrift::protocol::TType ftype;
  int16_t fid;

  xfer += iprot->readStructBegin(fname);

  using ::apache::thrift::protocol::TProtocolException;


  while (true)
  {
    xfer += iprot->readFieldBegin(fname, ftype, fid);
    if (ftype == ::apache::thrift::protocol::T_STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
        if (ftype == ::apache::thrift::protocol::T_I64) {
          xfer += iprot->readI64(this->user_id);
          this->__isset.user_id = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      case 2:
        if (ftype == ::apache::thrift::protocol::T_I64) {
          xfer += iprot->readI64(this->client_random);
          this->__isset.client_random = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      case 3:
        if (ftype == ::apache::thrift::protocol::T_STRING) {
          xfer += iprot->readString(this->client_mac);
          this->__isset.client_mac = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      case 4:
        if (ftype == ::apache::thrift::protocol::T_STRING) {
          xfer += iprot->readString(this->client_version);
          this->__isset.client_version = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      case 5:
        if (ftype == ::apache::thrift::protocol::T_STRING) {
          xfer += iprot->readString(this->check_sum);
          this->__isset.check_sum = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      default:
        xfer += iprot->skip(ftype);
        break;
    }
    xfer += iprot->readFieldEnd();
  }

  xfer += iprot->readStructEnd();

  return xfer;
}

uint32_t ClientPasswordLoginReq::write(::apache::thrift::protocol::TProtocol* oprot) const {
  uint32_t xfer = 0;
  apache::thrift::protocol::TOutputRecursionTracker tracker(*oprot);
  xfer += oprot->writeStructBegin("ClientPasswordLoginReq");

  xfer += oprot->writeFieldBegin("user_id", ::apache::thrift::protocol::T_I64, 1);
  xfer += oprot->writeI64(this->user_id);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldBegin("client_random", ::apache::thrift::protocol::T_I64, 2);
  xfer += oprot->writeI64(this->client_random);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldBegin("client_mac", ::apache::thrift::protocol::T_STRING, 3);
  xfer += oprot->writeString(this->client_mac);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldBegin("client_version", ::apache::thrift::protocol::T_STRING, 4);
  xfer += oprot->writeString(this->client_version);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldBegin("check_sum", ::apache::thrift::protocol::T_STRING, 5);
  xfer += oprot->writeString(this->check_sum);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldStop();
  xfer += oprot->writeStructEnd();
  return xfer;
}

void swap(ClientPasswordLoginReq &a, ClientPasswordLoginReq &b) {
  using ::std::swap;
  swap(a.user_id, b.user_id);
  swap(a.client_random, b.client_random);
  swap(a.client_mac, b.client_mac);
  swap(a.client_version, b.client_version);
  swap(a.check_sum, b.check_sum);
  swap(a.__isset, b.__isset);
}

ClientPasswordLoginReq::ClientPasswordLoginReq(const ClientPasswordLoginReq& other0) {
  user_id = other0.user_id;
  client_random = other0.client_random;
  client_mac = other0.client_mac;
  client_version = other0.client_version;
  check_sum = other0.check_sum;
  __isset = other0.__isset;
}
ClientPasswordLoginReq& ClientPasswordLoginReq::operator=(const ClientPasswordLoginReq& other1) {
  user_id = other1.user_id;
  client_random = other1.client_random;
  client_mac = other1.client_mac;
  client_version = other1.client_version;
  check_sum = other1.check_sum;
  __isset = other1.__isset;
  return *this;
}
void ClientPasswordLoginReq::printTo(std::ostream& out) const {
  using ::apache::thrift::to_string;
  out << "ClientPasswordLoginReq(";
  out << "user_id=" << to_string(user_id);
  out << ", " << "client_random=" << to_string(client_random);
  out << ", " << "client_mac=" << to_string(client_mac);
  out << ", " << "client_version=" << to_string(client_version);
  out << ", " << "check_sum=" << to_string(check_sum);
  out << ")";
}


ClientPasswordLoginReqChecksum::~ClientPasswordLoginReqChecksum() throw() {
}


void ClientPasswordLoginReqChecksum::__set_user_id(const int64_t val) {
  this->user_id = val;
}

void ClientPasswordLoginReqChecksum::__set_password(const std::string& val) {
  this->password = val;
}

void ClientPasswordLoginReqChecksum::__set_client_random(const int64_t val) {
  this->client_random = val;
}

void ClientPasswordLoginReqChecksum::__set_server_random(const int64_t val) {
  this->server_random = val;
}

uint32_t ClientPasswordLoginReqChecksum::read(::apache::thrift::protocol::TProtocol* iprot) {

  apache::thrift::protocol::TInputRecursionTracker tracker(*iprot);
  uint32_t xfer = 0;
  std::string fname;
  ::apache::thrift::protocol::TType ftype;
  int16_t fid;

  xfer += iprot->readStructBegin(fname);

  using ::apache::thrift::protocol::TProtocolException;


  while (true)
  {
    xfer += iprot->readFieldBegin(fname, ftype, fid);
    if (ftype == ::apache::thrift::protocol::T_STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
        if (ftype == ::apache::thrift::protocol::T_I64) {
          xfer += iprot->readI64(this->user_id);
          this->__isset.user_id = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      case 2:
        if (ftype == ::apache::thrift::protocol::T_STRING) {
          xfer += iprot->readString(this->password);
          this->__isset.password = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      case 3:
        if (ftype == ::apache::thrift::protocol::T_I64) {
          xfer += iprot->readI64(this->client_random);
          this->__isset.client_random = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      case 4:
        if (ftype == ::apache::thrift::protocol::T_I64) {
          xfer += iprot->readI64(this->server_random);
          this->__isset.server_random = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      default:
        xfer += iprot->skip(ftype);
        break;
    }
    xfer += iprot->readFieldEnd();
  }

  xfer += iprot->readStructEnd();

  return xfer;
}

uint32_t ClientPasswordLoginReqChecksum::write(::apache::thrift::protocol::TProtocol* oprot) const {
  uint32_t xfer = 0;
  apache::thrift::protocol::TOutputRecursionTracker tracker(*oprot);
  xfer += oprot->writeStructBegin("ClientPasswordLoginReqChecksum");

  xfer += oprot->writeFieldBegin("user_id", ::apache::thrift::protocol::T_I64, 1);
  xfer += oprot->writeI64(this->user_id);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldBegin("password", ::apache::thrift::protocol::T_STRING, 2);
  xfer += oprot->writeString(this->password);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldBegin("client_random", ::apache::thrift::protocol::T_I64, 3);
  xfer += oprot->writeI64(this->client_random);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldBegin("server_random", ::apache::thrift::protocol::T_I64, 4);
  xfer += oprot->writeI64(this->server_random);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldStop();
  xfer += oprot->writeStructEnd();
  return xfer;
}

void swap(ClientPasswordLoginReqChecksum &a, ClientPasswordLoginReqChecksum &b) {
  using ::std::swap;
  swap(a.user_id, b.user_id);
  swap(a.password, b.password);
  swap(a.client_random, b.client_random);
  swap(a.server_random, b.server_random);
  swap(a.__isset, b.__isset);
}

ClientPasswordLoginReqChecksum::ClientPasswordLoginReqChecksum(const ClientPasswordLoginReqChecksum& other2) {
  user_id = other2.user_id;
  password = other2.password;
  client_random = other2.client_random;
  server_random = other2.server_random;
  __isset = other2.__isset;
}
ClientPasswordLoginReqChecksum& ClientPasswordLoginReqChecksum::operator=(const ClientPasswordLoginReqChecksum& other3) {
  user_id = other3.user_id;
  password = other3.password;
  client_random = other3.client_random;
  server_random = other3.server_random;
  __isset = other3.__isset;
  return *this;
}
void ClientPasswordLoginReqChecksum::printTo(std::ostream& out) const {
  using ::apache::thrift::to_string;
  out << "ClientPasswordLoginReqChecksum(";
  out << "user_id=" << to_string(user_id);
  out << ", " << "password=" << to_string(password);
  out << ", " << "client_random=" << to_string(client_random);
  out << ", " << "server_random=" << to_string(server_random);
  out << ")";
}


ClientLoginRes::~ClientLoginRes() throw() {
}


void ClientLoginRes::__set_error_code(const int32_t val) {
  this->error_code = val;
}

void ClientLoginRes::__set_error_text(const std::string& val) {
  this->error_text = val;
}

void ClientLoginRes::__set_session_ticket(const std::string& val) {
  this->session_ticket = val;
}

uint32_t ClientLoginRes::read(::apache::thrift::protocol::TProtocol* iprot) {

  apache::thrift::protocol::TInputRecursionTracker tracker(*iprot);
  uint32_t xfer = 0;
  std::string fname;
  ::apache::thrift::protocol::TType ftype;
  int16_t fid;

  xfer += iprot->readStructBegin(fname);

  using ::apache::thrift::protocol::TProtocolException;


  while (true)
  {
    xfer += iprot->readFieldBegin(fname, ftype, fid);
    if (ftype == ::apache::thrift::protocol::T_STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
        if (ftype == ::apache::thrift::protocol::T_I32) {
          xfer += iprot->readI32(this->error_code);
          this->__isset.error_code = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      case 2:
        if (ftype == ::apache::thrift::protocol::T_STRING) {
          xfer += iprot->readString(this->error_text);
          this->__isset.error_text = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      case 3:
        if (ftype == ::apache::thrift::protocol::T_STRING) {
          xfer += iprot->readString(this->session_ticket);
          this->__isset.session_ticket = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      default:
        xfer += iprot->skip(ftype);
        break;
    }
    xfer += iprot->readFieldEnd();
  }

  xfer += iprot->readStructEnd();

  return xfer;
}

uint32_t ClientLoginRes::write(::apache::thrift::protocol::TProtocol* oprot) const {
  uint32_t xfer = 0;
  apache::thrift::protocol::TOutputRecursionTracker tracker(*oprot);
  xfer += oprot->writeStructBegin("ClientLoginRes");

  xfer += oprot->writeFieldBegin("error_code", ::apache::thrift::protocol::T_I32, 1);
  xfer += oprot->writeI32(this->error_code);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldBegin("error_text", ::apache::thrift::protocol::T_STRING, 2);
  xfer += oprot->writeString(this->error_text);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldBegin("session_ticket", ::apache::thrift::protocol::T_STRING, 3);
  xfer += oprot->writeString(this->session_ticket);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldStop();
  xfer += oprot->writeStructEnd();
  return xfer;
}

void swap(ClientLoginRes &a, ClientLoginRes &b) {
  using ::std::swap;
  swap(a.error_code, b.error_code);
  swap(a.error_text, b.error_text);
  swap(a.session_ticket, b.session_ticket);
  swap(a.__isset, b.__isset);
}

ClientLoginRes::ClientLoginRes(const ClientLoginRes& other4) {
  error_code = other4.error_code;
  error_text = other4.error_text;
  session_ticket = other4.session_ticket;
  __isset = other4.__isset;
}
ClientLoginRes& ClientLoginRes::operator=(const ClientLoginRes& other5) {
  error_code = other5.error_code;
  error_text = other5.error_text;
  session_ticket = other5.session_ticket;
  __isset = other5.__isset;
  return *this;
}
void ClientLoginRes::printTo(std::ostream& out) const {
  using ::apache::thrift::to_string;
  out << "ClientLoginRes(";
  out << "error_code=" << to_string(error_code);
  out << ", " << "error_text=" << to_string(error_text);
  out << ", " << "session_ticket=" << to_string(session_ticket);
  out << ")";
}


LogoutReq::~LogoutReq() throw() {
}


void LogoutReq::__set_user_id(const int64_t val) {
  this->user_id = val;
}

void LogoutReq::__set_session_ticket(const std::string& val) {
  this->session_ticket = val;
}

uint32_t LogoutReq::read(::apache::thrift::protocol::TProtocol* iprot) {

  apache::thrift::protocol::TInputRecursionTracker tracker(*iprot);
  uint32_t xfer = 0;
  std::string fname;
  ::apache::thrift::protocol::TType ftype;
  int16_t fid;

  xfer += iprot->readStructBegin(fname);

  using ::apache::thrift::protocol::TProtocolException;


  while (true)
  {
    xfer += iprot->readFieldBegin(fname, ftype, fid);
    if (ftype == ::apache::thrift::protocol::T_STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
        if (ftype == ::apache::thrift::protocol::T_I64) {
          xfer += iprot->readI64(this->user_id);
          this->__isset.user_id = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      case 2:
        if (ftype == ::apache::thrift::protocol::T_STRING) {
          xfer += iprot->readString(this->session_ticket);
          this->__isset.session_ticket = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      default:
        xfer += iprot->skip(ftype);
        break;
    }
    xfer += iprot->readFieldEnd();
  }

  xfer += iprot->readStructEnd();

  return xfer;
}

uint32_t LogoutReq::write(::apache::thrift::protocol::TProtocol* oprot) const {
  uint32_t xfer = 0;
  apache::thrift::protocol::TOutputRecursionTracker tracker(*oprot);
  xfer += oprot->writeStructBegin("LogoutReq");

  xfer += oprot->writeFieldBegin("user_id", ::apache::thrift::protocol::T_I64, 1);
  xfer += oprot->writeI64(this->user_id);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldBegin("session_ticket", ::apache::thrift::protocol::T_STRING, 2);
  xfer += oprot->writeString(this->session_ticket);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldStop();
  xfer += oprot->writeStructEnd();
  return xfer;
}

void swap(LogoutReq &a, LogoutReq &b) {
  using ::std::swap;
  swap(a.user_id, b.user_id);
  swap(a.session_ticket, b.session_ticket);
  swap(a.__isset, b.__isset);
}

LogoutReq::LogoutReq(const LogoutReq& other6) {
  user_id = other6.user_id;
  session_ticket = other6.session_ticket;
  __isset = other6.__isset;
}
LogoutReq& LogoutReq::operator=(const LogoutReq& other7) {
  user_id = other7.user_id;
  session_ticket = other7.session_ticket;
  __isset = other7.__isset;
  return *this;
}
void LogoutReq::printTo(std::ostream& out) const {
  using ::apache::thrift::to_string;
  out << "LogoutReq(";
  out << "user_id=" << to_string(user_id);
  out << ", " << "session_ticket=" << to_string(session_ticket);
  out << ")";
}


LogoutRes::~LogoutRes() throw() {
}


void LogoutRes::__set_error_code(const int32_t val) {
  this->error_code = val;
}

void LogoutRes::__set_user_id(const int64_t val) {
  this->user_id = val;
}

uint32_t LogoutRes::read(::apache::thrift::protocol::TProtocol* iprot) {

  apache::thrift::protocol::TInputRecursionTracker tracker(*iprot);
  uint32_t xfer = 0;
  std::string fname;
  ::apache::thrift::protocol::TType ftype;
  int16_t fid;

  xfer += iprot->readStructBegin(fname);

  using ::apache::thrift::protocol::TProtocolException;


  while (true)
  {
    xfer += iprot->readFieldBegin(fname, ftype, fid);
    if (ftype == ::apache::thrift::protocol::T_STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
        if (ftype == ::apache::thrift::protocol::T_I32) {
          xfer += iprot->readI32(this->error_code);
          this->__isset.error_code = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      case 2:
        if (ftype == ::apache::thrift::protocol::T_I64) {
          xfer += iprot->readI64(this->user_id);
          this->__isset.user_id = true;
        } else {
          xfer += iprot->skip(ftype);
        }
        break;
      default:
        xfer += iprot->skip(ftype);
        break;
    }
    xfer += iprot->readFieldEnd();
  }

  xfer += iprot->readStructEnd();

  return xfer;
}

uint32_t LogoutRes::write(::apache::thrift::protocol::TProtocol* oprot) const {
  uint32_t xfer = 0;
  apache::thrift::protocol::TOutputRecursionTracker tracker(*oprot);
  xfer += oprot->writeStructBegin("LogoutRes");

  xfer += oprot->writeFieldBegin("error_code", ::apache::thrift::protocol::T_I32, 1);
  xfer += oprot->writeI32(this->error_code);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldBegin("user_id", ::apache::thrift::protocol::T_I64, 2);
  xfer += oprot->writeI64(this->user_id);
  xfer += oprot->writeFieldEnd();

  xfer += oprot->writeFieldStop();
  xfer += oprot->writeStructEnd();
  return xfer;
}

void swap(LogoutRes &a, LogoutRes &b) {
  using ::std::swap;
  swap(a.error_code, b.error_code);
  swap(a.user_id, b.user_id);
  swap(a.__isset, b.__isset);
}

LogoutRes::LogoutRes(const LogoutRes& other8) {
  error_code = other8.error_code;
  user_id = other8.user_id;
  __isset = other8.__isset;
}
LogoutRes& LogoutRes::operator=(const LogoutRes& other9) {
  error_code = other9.error_code;
  user_id = other9.user_id;
  __isset = other9.__isset;
  return *this;
}
void LogoutRes::printTo(std::ostream& out) const {
  using ::apache::thrift::to_string;
  out << "LogoutRes(";
  out << "error_code=" << to_string(error_code);
  out << ", " << "user_id=" << to_string(user_id);
  out << ")";
}

}} // namespace
