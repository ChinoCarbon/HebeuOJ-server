getType(conclusion) {
      if(conclusion === "ACCEPTED")
      {
        return "success"
      } else if(conclusion === "WRONG_ANSWER")
      {
        return "danger"
      } else if(conclusion === "RUNTIME_ERROR")
      {
        return "info"
      } else if(conclusion === "TIME_LIMIT_EXCEEDED")
      {
        return ""
      } else
      {
        return ""
      }
    },
    getStatement(conclusion) {
      if(conclusion === "ACCEPTED")
      {
        return "答案正确 Accepted!"
      } else if(conclusion === "WRONG_ANSWER")
      {
        return "答案错误 Wrong Answer!"
      } else if(conclusion === "RUNTIME_ERROR")
      {
        return "运行时错误 Runtime Error!"
      } else if(conclusion === "TIME_LIMIT_EXCEEDED")
      {
        return "超出时间限制 Time Limit Exceeded!"
      } else
      {
        return "未知错误 Unknown Error!"
      }
    }