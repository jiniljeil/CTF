// Decompiled with JetBrains decompiler
// Type: MVC.Logs.Log
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.Extensions.Logging;

#nullable disable
namespace MVC.Logs
{
  public class Log
  {
    private readonly ILogger<Log> _logger;

    public Log(ILogger<Log> logger) => this._logger = logger;
  }
}
