// Decompiled with JetBrains decompiler
// Type: MVC.Migrations.Init
// Assembly: MVC, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null
// MVID: C0D10978-DBF5-45D2-99BC-5BF002D0A709
// Assembly location: \\wsl.localhost\Ubuntu\home\ubuntu\CTF\CCE\precious\user-MVC.dll

using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using Microsoft.EntityFrameworkCore.Migrations;
using MVC.Data;
using System;

#nullable disable
namespace MVC.Migrations
{
  [DbContext(typeof (ApplicationDbContext))]
  [Migration("20191121164734_Init")]
  public class Init : Migration
  {
    protected virtual void Up(MigrationBuilder migrationBuilder)
    {
      migrationBuilder.CreateTable("AspNetRoles", table => new
      {
        Id = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        Name = table.Column<string>((string) null, new bool?(), new int?(256), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        NormalizedName = table.Column<string>((string) null, new bool?(), new int?(256), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        ConcurrencyStamp = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?())
      }, (string) null, table => table.PrimaryKey("PK_AspNetRoles", x => x.Id));
      migrationBuilder.CreateTable("AspNetUsers", table => new
      {
        Id = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        UserName = table.Column<string>((string) null, new bool?(), new int?(256), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        NormalizedUserName = table.Column<string>((string) null, new bool?(), new int?(256), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        Email = table.Column<string>((string) null, new bool?(), new int?(256), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        NormalizedEmail = table.Column<string>((string) null, new bool?(), new int?(256), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        EmailConfirmed = table.Column<bool>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        PasswordHash = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        SecurityStamp = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        ConcurrencyStamp = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        PhoneNumber = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        PhoneNumberConfirmed = table.Column<bool>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        TwoFactorEnabled = table.Column<bool>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        LockoutEnd = table.Column<DateTimeOffset>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        LockoutEnabled = table.Column<bool>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        AccessFailedCount = table.Column<int>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?())
      }, (string) null, table => table.PrimaryKey("PK_AspNetUsers", x => x.Id));
      migrationBuilder.CreateTable("Contact", table => new
      {
        ContactId = table.Column<int>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()).Annotation("SqlServer:ValueGenerationStrategy", (object) (SqlServerValueGenerationStrategy) 1),
        OwnerID = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        Name = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        Address = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        City = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        State = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        Zip = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        Email = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        Status = table.Column<int>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?())
      }, (string) null, table => table.PrimaryKey("PK_Contact", x => x.ContactId));
      migrationBuilder.CreateTable("AspNetRoleClaims", table => new
      {
        Id = table.Column<int>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()).Annotation("SqlServer:ValueGenerationStrategy", (object) (SqlServerValueGenerationStrategy) 1),
        RoleId = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        ClaimType = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        ClaimValue = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?())
      }, (string) null, table =>
      {
        table.PrimaryKey("PK_AspNetRoleClaims", x => x.Id);
        table.ForeignKey("FK_AspNetRoleClaims_AspNetRoles_RoleId", x => x.RoleId, "AspNetRoles", "Id", (string) null, (ReferentialAction) 0, (ReferentialAction) 2);
      });
      migrationBuilder.CreateTable("AspNetUserClaims", table => new
      {
        Id = table.Column<int>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()).Annotation("SqlServer:ValueGenerationStrategy", (object) (SqlServerValueGenerationStrategy) 1),
        UserId = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        ClaimType = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        ClaimValue = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?())
      }, (string) null, table =>
      {
        table.PrimaryKey("PK_AspNetUserClaims", x => x.Id);
        table.ForeignKey("FK_AspNetUserClaims_AspNetUsers_UserId", x => x.UserId, "AspNetUsers", "Id", (string) null, (ReferentialAction) 0, (ReferentialAction) 2);
      });
      migrationBuilder.CreateTable("AspNetUserLogins", table => new
      {
        LoginProvider = table.Column<string>((string) null, new bool?(), new int?(128), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        ProviderKey = table.Column<string>((string) null, new bool?(), new int?(128), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        ProviderDisplayName = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?()),
        UserId = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?())
      }, (string) null, table =>
      {
        table.PrimaryKey("PK_AspNetUserLogins", x => new
        {
          LoginProvider = x.LoginProvider,
          ProviderKey = x.ProviderKey
        });
        table.ForeignKey("FK_AspNetUserLogins_AspNetUsers_UserId", x => x.UserId, "AspNetUsers", "Id", (string) null, (ReferentialAction) 0, (ReferentialAction) 2);
      });
      migrationBuilder.CreateTable("AspNetUserRoles", table => new
      {
        UserId = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        RoleId = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?())
      }, (string) null, table =>
      {
        table.PrimaryKey("PK_AspNetUserRoles", x => new
        {
          UserId = x.UserId,
          RoleId = x.RoleId
        });
        table.ForeignKey("FK_AspNetUserRoles_AspNetRoles_RoleId", x => x.RoleId, "AspNetRoles", "Id", (string) null, (ReferentialAction) 0, (ReferentialAction) 2);
        table.ForeignKey("FK_AspNetUserRoles_AspNetUsers_UserId", x => x.UserId, "AspNetUsers", "Id", (string) null, (ReferentialAction) 0, (ReferentialAction) 2);
      });
      migrationBuilder.CreateTable("AspNetUserTokens", table => new
      {
        UserId = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        LoginProvider = table.Column<string>((string) null, new bool?(), new int?(128), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        Name = table.Column<string>((string) null, new bool?(), new int?(128), false, (string) null, false, (object) null, (string) null, (string) null, new bool?()),
        Value = table.Column<string>((string) null, new bool?(), new int?(), false, (string) null, true, (object) null, (string) null, (string) null, new bool?())
      }, (string) null, table =>
      {
        table.PrimaryKey("PK_AspNetUserTokens", x => new
        {
          UserId = x.UserId,
          LoginProvider = x.LoginProvider,
          Name = x.Name
        });
        table.ForeignKey("FK_AspNetUserTokens_AspNetUsers_UserId", x => x.UserId, "AspNetUsers", "Id", (string) null, (ReferentialAction) 0, (ReferentialAction) 2);
      });
      migrationBuilder.CreateIndex("IX_AspNetRoleClaims_RoleId", "AspNetRoleClaims", "RoleId", (string) null, false, (string) null);
      migrationBuilder.CreateIndex("RoleNameIndex", "AspNetRoles", "NormalizedName", (string) null, true, "[NormalizedName] IS NOT NULL");
      migrationBuilder.CreateIndex("IX_AspNetUserClaims_UserId", "AspNetUserClaims", "UserId", (string) null, false, (string) null);
      migrationBuilder.CreateIndex("IX_AspNetUserLogins_UserId", "AspNetUserLogins", "UserId", (string) null, false, (string) null);
      migrationBuilder.CreateIndex("IX_AspNetUserRoles_RoleId", "AspNetUserRoles", "RoleId", (string) null, false, (string) null);
      migrationBuilder.CreateIndex("EmailIndex", "AspNetUsers", "NormalizedEmail", (string) null, false, (string) null);
      migrationBuilder.CreateIndex("UserNameIndex", "AspNetUsers", "NormalizedUserName", (string) null, true, "[NormalizedUserName] IS NOT NULL");
    }

    protected virtual void Down(MigrationBuilder migrationBuilder)
    {
      migrationBuilder.DropTable("AspNetRoleClaims", (string) null);
      migrationBuilder.DropTable("AspNetUserClaims", (string) null);
      migrationBuilder.DropTable("AspNetUserLogins", (string) null);
      migrationBuilder.DropTable("AspNetUserRoles", (string) null);
      migrationBuilder.DropTable("AspNetUserTokens", (string) null);
      migrationBuilder.DropTable("Contact", (string) null);
      migrationBuilder.DropTable("AspNetRoles", (string) null);
      migrationBuilder.DropTable("AspNetUsers", (string) null);
    }

    protected virtual void BuildTargetModel(ModelBuilder modelBuilder)
    {
      modelBuilder.HasAnnotation("ProductVersion", (object) "2.2.6-servicing-10079").HasAnnotation("Relational:MaxIdentifierLength", (object) 128).HasAnnotation("SqlServer:ValueGenerationStrategy", (object) (SqlServerValueGenerationStrategy) 1);
      modelBuilder.Entity("MVC.Models.Contact", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<int>("ContactId").ValueGeneratedOnAdd().HasAnnotation("SqlServer:ValueGenerationStrategy", (object) (SqlServerValueGenerationStrategy) 1);
        b.Property<string>("Address");
        b.Property<string>("City");
        b.Property<string>("Email");
        b.Property<string>("Name");
        b.Property<string>("OwnerID");
        b.Property<string>("State");
        b.Property<int>("Status");
        b.Property<string>("Zip");
        b.HasKey(new string[1]{ "ContactId" });
        RelationalEntityTypeBuilderExtensions.ToTable(b, "Contact");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityRole", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<string>("Id").ValueGeneratedOnAdd();
        b.Property<string>("ConcurrencyStamp").IsConcurrencyToken(true);
        b.Property<string>("Name").HasMaxLength(256);
        b.Property<string>("NormalizedName").HasMaxLength(256);
        b.HasKey(new string[1]{ "Id" });
        RelationalIndexBuilderExtensions.HasFilter(RelationalIndexBuilderExtensions.HasName(b.HasIndex(new string[1]
        {
          "NormalizedName"
        }).IsUnique(true), "RoleNameIndex"), "[NormalizedName] IS NOT NULL");
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetRoles");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityRoleClaim<string>", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<int>("Id").ValueGeneratedOnAdd().HasAnnotation("SqlServer:ValueGenerationStrategy", (object) (SqlServerValueGenerationStrategy) 1);
        b.Property<string>("ClaimType");
        b.Property<string>("ClaimValue");
        b.Property<string>("RoleId").IsRequired(true);
        b.HasKey(new string[1]{ "Id" });
        b.HasIndex(new string[1]{ "RoleId" });
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetRoleClaims");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUser", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<string>("Id").ValueGeneratedOnAdd();
        b.Property<int>("AccessFailedCount");
        b.Property<string>("ConcurrencyStamp").IsConcurrencyToken(true);
        b.Property<string>("Email").HasMaxLength(256);
        b.Property<bool>("EmailConfirmed");
        b.Property<bool>("LockoutEnabled");
        b.Property<DateTimeOffset?>("LockoutEnd");
        b.Property<string>("NormalizedEmail").HasMaxLength(256);
        b.Property<string>("NormalizedUserName").HasMaxLength(256);
        b.Property<string>("PasswordHash");
        b.Property<string>("PhoneNumber");
        b.Property<bool>("PhoneNumberConfirmed");
        b.Property<string>("SecurityStamp");
        b.Property<bool>("TwoFactorEnabled");
        b.Property<string>("UserName").HasMaxLength(256);
        b.HasKey(new string[1]{ "Id" });
        RelationalIndexBuilderExtensions.HasName(b.HasIndex(new string[1]
        {
          "NormalizedEmail"
        }), "EmailIndex");
        RelationalIndexBuilderExtensions.HasFilter(RelationalIndexBuilderExtensions.HasName(b.HasIndex(new string[1]
        {
          "NormalizedUserName"
        }).IsUnique(true), "UserNameIndex"), "[NormalizedUserName] IS NOT NULL");
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetUsers");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserClaim<string>", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<int>("Id").ValueGeneratedOnAdd().HasAnnotation("SqlServer:ValueGenerationStrategy", (object) (SqlServerValueGenerationStrategy) 1);
        b.Property<string>("ClaimType");
        b.Property<string>("ClaimValue");
        b.Property<string>("UserId").IsRequired(true);
        b.HasKey(new string[1]{ "Id" });
        b.HasIndex(new string[1]{ "UserId" });
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetUserClaims");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserLogin<string>", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<string>("LoginProvider").HasMaxLength(128);
        b.Property<string>("ProviderKey").HasMaxLength(128);
        b.Property<string>("ProviderDisplayName");
        b.Property<string>("UserId").IsRequired(true);
        b.HasKey(new string[2]
        {
          "LoginProvider",
          "ProviderKey"
        });
        b.HasIndex(new string[1]{ "UserId" });
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetUserLogins");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserRole<string>", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<string>("UserId");
        b.Property<string>("RoleId");
        b.HasKey(new string[2]{ "UserId", "RoleId" });
        b.HasIndex(new string[1]{ "RoleId" });
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetUserRoles");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserToken<string>", (Action<EntityTypeBuilder>) (b =>
      {
        b.Property<string>("UserId");
        b.Property<string>("LoginProvider").HasMaxLength(128);
        b.Property<string>("Name").HasMaxLength(128);
        b.Property<string>("Value");
        b.HasKey(new string[3]
        {
          "UserId",
          "LoginProvider",
          "Name"
        });
        RelationalEntityTypeBuilderExtensions.ToTable(b, "AspNetUserTokens");
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityRoleClaim<string>", (Action<EntityTypeBuilder>) (b => b.HasOne("Microsoft.AspNetCore.Identity.IdentityRole", (string) null).WithMany((string) null).HasForeignKey(new string[1]
      {
        "RoleId"
      }).OnDelete((DeleteBehavior) 3)));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserClaim<string>", (Action<EntityTypeBuilder>) (b => b.HasOne("Microsoft.AspNetCore.Identity.IdentityUser", (string) null).WithMany((string) null).HasForeignKey(new string[1]
      {
        "UserId"
      }).OnDelete((DeleteBehavior) 3)));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserLogin<string>", (Action<EntityTypeBuilder>) (b => b.HasOne("Microsoft.AspNetCore.Identity.IdentityUser", (string) null).WithMany((string) null).HasForeignKey(new string[1]
      {
        "UserId"
      }).OnDelete((DeleteBehavior) 3)));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserRole<string>", (Action<EntityTypeBuilder>) (b =>
      {
        b.HasOne("Microsoft.AspNetCore.Identity.IdentityRole", (string) null).WithMany((string) null).HasForeignKey(new string[1]
        {
          "RoleId"
        }).OnDelete((DeleteBehavior) 3);
        b.HasOne("Microsoft.AspNetCore.Identity.IdentityUser", (string) null).WithMany((string) null).HasForeignKey(new string[1]
        {
          "UserId"
        }).OnDelete((DeleteBehavior) 3);
      }));
      modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserToken<string>", (Action<EntityTypeBuilder>) (b => b.HasOne("Microsoft.AspNetCore.Identity.IdentityUser", (string) null).WithMany((string) null).HasForeignKey(new string[1]
      {
        "UserId"
      }).OnDelete((DeleteBehavior) 3)));
    }
  }
}
