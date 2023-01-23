using System.Data;
using System.Text;
using System.Xml;
using System.Xml.Serialization;
using Microsoft.AnalysisServices.AdomdClient;

namespace AdomdClientConsole
{
    class Program
    {
        // Parameters:
        // 0 - adomd connection string
        // 1 - request type: schema; query
        // 2 - request text
        // 3 - properties (as xml). can be empty string ("")
        //
        // <Properties>
        //   <PropertyList>
        //     <Catalog>Adventure Works DW 2008 SE</Catalog>
        //   </PropertyList>
        // </Properties>
        //
        // 4 - restrictions (as xml). can be empty string ("")
        //
        // <Restrictions>  
        //   <RestrictionList>  
        //     <CUBE_NAME>Adventure Works</CUBE_NAME>  
        //   </RestrictionList>  
        // </Restrictions>  
        //
        // Examples:
        // "Data source=http://bi.syncfusion.com/olap/msmdpump.dll" schema MDSCHEMA_CUBES
        // "Data source=http://bi.syncfusion.com/olap/msmdpump.dll" schema MDSCHEMA_CUBES "" "<Restrictions><RestrictionList><CUBE_NAME>Adventure Works</CUBE_NAME></RestrictionList></Restrictions>"
        // "Data source=http://bi.syncfusion.com/olap/msmdpump.dll" schema MDSCHEMA_CUBES "<Properties><PropertyList><Catalog>Adventure Works DW 2008 SE</Catalog></PropertyList></Properties>" "<Restrictions><RestrictionList><CUBE_NAME>Adventure Works</CUBE_NAME></RestrictionList></Restrictions>"
        // "Data source=http://bi.syncfusion.com/olap/msmdpump.dll"  query "select from [Adventure Works]"
        // "Data source=http://bi.syncfusion.com/olap/msmdpump.dll"  query "select from [Adventure Works]" "<Properties><PropertyList><Catalog>Adventure Works DW 2008 SE</Catalog></PropertyList></Properties>"
        static void Main(string[] args)
        {
            if (args.Length > 0)
            {
                AdomdConnection adomdConnection = new AdomdConnection();
                try
                {
                    adomdConnection.ConnectionString = args[0];
                    adomdConnection.Open();
                    if(args.Length > 2)
                    {
                        if(args[1] == "schema")
                        {
                            AdomdRestrictionCollection restrictions = new AdomdRestrictionCollection();
                            AdomdPropertyCollection properties = new AdomdPropertyCollection();

                            if (args.Length > 3 && !String.IsNullOrEmpty(args[3]))
                            {
                                XmlDocument doc = new XmlDocument();
                                doc.LoadXml(args[3]);
                                foreach (XmlNode xmlNode in doc.SelectNodes("//Properties/PropertyList/*"))
                                {
                                    properties.Add(xmlNode.Name, xmlNode.InnerText);
                                }
                            }

                            if (args.Length > 4 && !String.IsNullOrEmpty(args[4]))
                            {
                                XmlDocument doc = new XmlDocument();
                                doc.LoadXml(args[4]);
                                foreach(XmlNode xmlNode in doc.SelectNodes("//Restrictions/RestrictionList/*"))
                                {
                                    restrictions.Add(xmlNode.Name, xmlNode.InnerText);
                                }
                            }

                            DataSet dataSet = adomdConnection.GetSchemaDataSet(args[2], null, restrictions, true, properties);
                            using (var memoryStream = new MemoryStream())
                            {
                                using (TextWriter streamWriter = new StreamWriter(memoryStream))
                                {
                                    var xmlSerializer = new XmlSerializer(typeof(DataSet));
                                    xmlSerializer.Serialize(streamWriter, dataSet);
                                    Console.Out.WriteLine(Encoding.UTF8.GetString(memoryStream.ToArray()));
                                }
                            }
                        }
                        else if(args[1] == "query")
                        {
                            AdomdCommand adomdCommand = adomdConnection.CreateCommand();
                            adomdCommand.CommandText = args[2];

                            AdomdPropertyCollection properties = new AdomdPropertyCollection();

                            if (args.Length > 3 && !String.IsNullOrEmpty(args[3]))
                            {
                                XmlDocument doc = new XmlDocument();
                                doc.LoadXml(args[3]);
                                foreach (XmlNode xmlNode in doc.SelectNodes("//Properties/PropertyList/*"))
                                {
                                    adomdCommand.Properties.Add(xmlNode.Name, xmlNode.InnerText);
                                }
                            }


                            using (XmlReader xmlReader = adomdCommand.ExecuteXmlReader())
                            {
                                XmlDocument doc = new XmlDocument();
                                doc.Load(xmlReader);
                                Console.Out.WriteLine(doc.InnerXml);
                            }
                        }
                    }
                }
                catch(Exception e)
                {
                    Console.Error.WriteLine(e.ToString());
                }
                finally
                {
                    if (adomdConnection.State == System.Data.ConnectionState.Open)
                    {
                        adomdConnection.Close();
                    }
                }
                //Console.Read();
            }
        }
    }
}
