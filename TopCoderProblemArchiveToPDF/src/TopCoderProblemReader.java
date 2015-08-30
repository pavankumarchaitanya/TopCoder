import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
public class TopCoderProblemReader {
	static Integer incrementBy=1;
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
 
	for(int i =7388;i<13000;i= i+ incrementBy){
		System.out.println("Processing problem Number : " + i);
		//Thread.sleep(500);
		getProblemStatementForProblemNumber(i);
	}
		System.out.println(getProblemStatementForProblemNumber(13009));
	}
static String getProblemStatementForProblemNumber(Integer ProblemNumber) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
	String URL = "http://community.topcoder.com/stat?c=problem_statement&pm="+ProblemNumber	;
	String ProblemStatement = "";
			 final WebClient webClient = new WebClient();

			    // Get the first page
			    final HtmlPage page1 = webClient.getPage(URL);
			    String fileName = "";
for(String line : page1.asText().split("\n"))
{
if (line.contains("Problem Statement for"))
{
	fileName = line.replace("Problem Statement for", "").trim();	
}
}
			    List<HtmlTableDataCell> problemStmt =  (List<HtmlTableDataCell>) page1.getByXPath("//td[@class='problemText']");

			    for(HtmlTableDataCell s:problemStmt)
			    {
			    	ProblemStatement = s.asXml();
			    }
			    
			    writeProblemToFile(ProblemStatement,fileName);
	return ProblemStatement;
	
}
static void writeProblemToFile(String s,String fileName) throws FileNotFoundException
{
	if(!fileName.isEmpty())
	{
		incrementBy=1;
		PrintWriter out = new PrintWriter(fileName + ".html");
		out.println(s);
		out.close();
	}else{
		incrementBy*=2;
	}
	}
}
