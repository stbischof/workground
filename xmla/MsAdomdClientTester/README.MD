## Linux Dotnet Set Up

Manual
https://docs.microsoft.com/en-us/dotnet/core/install/linux-scripted-manual#scripted-install

Download dotnet-install.sh from
https://dot.net/v1/dotnet-install.sh

Make the script executable
chmod u+x dotnet-install.sh

Install the current release of .Net
./dotnet-install.sh -c Current 

Add two lines to ~/.bashrc (after, restart system to apply):
export DOTNET_ROOT=$HOME/.dotnet
export PATH=$PATH:$DOTNET_ROOT

To check the installed version run
dotnet --info

## Linux Visual Studio Code Set Up

Download and set up Visual Studio Code from 
https://code.visualstudio.com/download

In Visual Studio Code install "C# for Visual Studio Code (powered by OmniSharp)" extension.

## Build/Run Project
In Visual Studio Code open folder with MsAdomdClientTester project.
Open New Terminal

dotnet build

dotnet run

See parameters description in Program.cs

Examples:
dotnet run "Data source=http://bi.syncfusion.com/olap/msmdpump.dll;UID=Domain\User;PWD=UserDomainPassword" schema MDSCHEMA_CUBES

dotnet run "Data source=http://bi.syncfusion.com/olap/msmdpump.dll;UID=Domain\User;PWD=UserDomainPassword" schema MDSCHEMA_CUBES "" "<Restrictions><RestrictionList><CUBE_NAME>Adventure Works</CUBE_NAME></RestrictionList></Restrictions>"

dotnet run "Data source=http://bi.syncfusion.com/olap/msmdpump.dll;UID=Domain\User;PWD=UserDomainPassword" schema MDSCHEMA_CUBES "<Properties><PropertyList><Catalog>Adventure Works DW 2008 SE</Catalog></PropertyList></Properties>" "<Restrictions><RestrictionList><CUBE_NAME>Adventure Works</CUBE_NAME></RestrictionList></Restrictions>"

dotnet run "Data source=http://bi.syncfusion.com/olap/msmdpump.dll;UID=Domain\User;PWD=UserDomainPassword"  query "select from [Adventure Works]"

dotnet run "Data source=http://bi.syncfusion.com/olap/msmdpump.dll;UID=Domain\User;PWD=UserDomainPassword"  query "select from [Adventure Works]" "<Properties><PropertyList><Catalog>Adventure Works DW 2008 SE</Catalog></PropertyList></Properties>"


-----
old snippets

dotnet add package Microsoft.AnalysisServices.AdomdClient.NetCore.retail.amd64 --version 19.46.0

dotnet build MsAdomdClientTester.csproj /property:GenerateFullPaths=true /consoleloggerparameters:NoSummary

dotnet bin/Debug/net6.0/MsAdomdClientTester.dll "Data source=http://bi.syncfusion.com/olap/msmdpump.dll;UID=Domain\User;PWD=UserDomainPassword" schema MDSCHEMA_CUBES

dotnet add package Microsoft.AnalysisServices.AdomdClient.retail.amd64 --version 19.46.0

dotnet add package System.Configuration.ConfigurationManager
