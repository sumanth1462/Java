/*Write a program to read the contents of a file whose location is taken using command-line argument. Verify whether path exists or not. If path given is incorrect, then it should keep on asking the correct path from the user until user enter the correct path of a file/directory.  
Now, if the path entered is of the directory, then it should display the names of all files & directories in that directory. Now ask the user to enter the name of a file and verify if the file mentioned exists this directory or not. If it exists, then perform the following task: 
a) Print the following information about the file: size, readable/writable. b) Ask the user to enter a message and save this message in the file such that previous contents of the file are not erased. c) Now read the contents of this file display them on the console screen.  
Note: Make use of Character-stream class to read/write contents. Don’t make any separate instance variable other than the ones specified in above scenario. Also, make use proper exception handling by using inbuilt exception-classes and also make your own custom exception class to deal with the exceptions such as user entering a negative no.*/ 
import java.io.*;
import java.util.*;
class Fh
{
	public static void main(String args[]) 
	{
		String path=args[0];
		File fr=new File(path);
		while(true)
		{
			int pp=0,nmf=0;
			if(fr.exists())
			{
				int pfn=0;
				if(fr.isDirectory())
				{       
					pfn=1;
					String names[]=fr.list();
					System.out.println("contents inside the directory:");
					File fr1;
					for(int i=0;i<names.length;i++)
					{

						fr1=new File(fr,names[i]);
						if(fr1.isFile() || fr1.isDirectory())
						{
							System.out.println(names[i]);
						}
					}
					Scanner sc=new Scanner(System.in);
					System.out.println("enter a file name:");
					String fname=sc.nextLine();
					int ep=0;
					for(int i=0;i<names.length;i++)
					{
						if(fname.equals(names[i]))
						{
							ep=1;
							System.out.println("File size:"+fr.length()+"  "+"Is it readable:"+fr.canRead()+"  "+"Is it writable:"+fr.canWrite());
							System.out.println("Enter the path of the matched filename:");
							String path1=sc.nextLine();
							String h=fr.getPath()+"\\"+names[i];
							File f=new File(path1);
							if((f.getPath().equals(h)) && (f.getName().equals(names[i])))
							{ 
								FileWriter fr2=null;
								FileReader fr3=null;
								try
								{
									fr2=new FileWriter(f,true);
									System.out.println("Enter a message:");
									String message=sc.nextLine();
									String mess="\n"+message;
									if(f.length()==0)
									{
										fr2.write(message);
									}
									else
									{
										fr2.write(mess);
									}
									fr2.close();
									System.out.println("Content inside the file "+f.getName()+":");
									fr3=new FileReader(f);
									int c=0;
									while(c!=-1)
									{
										c=fr3.read();
										if(c==-1)
										{
											break;	
										}
										System.out.print((char)c);
									}
									fr3.close();
									pp=1;
									break;
								}
								catch(IOException e)
								{
										System.out.println("Error ");
								}					
							}
							else
							{
								System.out.println("Did not entered the path of matched file.");
								nmf=1;
								break;
							}						
						}
					}
					if(ep==0)
					{
						System.out.println("No match found for "+fname+".");
						break;
					}	
				}
				if(pfn==0)
				{
				System.out.println("The path u entered contains file name.");                
				break;
				}			
			}
			else
			{
				Scanner sc=new Scanner(System.in);
				System.out.println("Enter correct path");
				String f=sc.nextLine();
				fr=new File(f);
			}
			if(pp==1||nmf==1)
			{
				break;
			}
		}
				
	}
	
}