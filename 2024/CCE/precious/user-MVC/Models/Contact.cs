// Decompiled with JetBrains decompiler
// Type: MVC.Models.Contact
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using System.ComponentModel.DataAnnotations;

#nullable disable
namespace MVC.Models
{
  public class Contact
  {
    public int ContactId { get; set; }

    public string OwnerID { get; set; }

    public string Name { get; set; }

    public string Address { get; set; }

    public string City { get; set; }

    public string State { get; set; }

    public string Zip { get; set; }

    [DataType(DataType.EmailAddress)]
    public string Email { get; set; }

    public ContactStatus Status { get; set; }
  }
}
