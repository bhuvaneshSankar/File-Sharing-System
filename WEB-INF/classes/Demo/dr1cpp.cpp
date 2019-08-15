#include<cstdio>            
#include<iostream>
#include<fstream>
#include<cstdlib>
#include<string.h>
#include<stddef.h>
#include<sys/types.h>
#include<sys/stat.h>
#include<dirent.h>
#include<stdbool.h>
#include<vector>
#include<string>
#include <windows.h>
#include<jni.h>
#include"Demo_Drmng.h"

using namespace std;
//string path = "C:\\Users\\Administrator\\Desktop\\javatrav\\new\\";
string path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\dm\\users\\";

bool isDir(string dir){
    struct stat fileInfo;
    stat(dir.c_str(), &fileInfo);
    if (S_ISDIR(fileInfo.st_mode)){
        return true;
    }else{
        return false;
    }
}
//bool createDirectory(char dirName[])
JNIEXPORT jboolean JNICALL Java_Demo_Drmng_createDirectory
  (JNIEnv *env, jobject obj, jstring dirName)
{
	int status;
	
     const char* dirNamechar = env->GetStringUTFChars(dirName, 0);

  string nameOfDirectory(dirNamechar);
//  nameOfDirectory = path + nameOfDirectory;

  int n = nameOfDirectory.length(); 
  char char_array[n + 1]; 
  strcpy(char_array, nameOfDirectory.c_str());

    status = mkdir(char_array);
	
	   env->ReleaseStringUTFChars(dirName, dirNamechar);
     if(!status)
  //  printf("directory created");
    return true;
  else{
  //  printf("unable to create");
    return false;
    exit(1);
  }

  return true;	
}

void DeleteFolder(const char* szFolderPath)
{
string strFileFilter;

strFileFilter = szFolderPath;
strFileFilter += "\\*.*";

WIN32_FIND_DATA win32FindData; //struct to hold file information

HANDLE hFile = FindFirstFile(strFileFilter.c_str(), &win32FindData);

while (FindNextFile(hFile, &win32FindData))
{
string strFilePath; // full file path
string strFileName; // file name with extension only

strFilePath = szFolderPath;
strFilePath += "\\";
strFilePath += win32FindData.cFileName;
strFileName = win32FindData.cFileName;

//If is dots
if (strFileName == "." ||
strFileName == "..")
{
continue;
}

int iFindDot = strFileName.find(".");

//Assume it is a directory because no '.' was found
if (iFindDot < 0) //not extension
{
//Recursive call
DeleteFolder(strFilePath.c_str());
}

DeleteFile(strFilePath.c_str());
}

FindClose(hFile); //release handle otherwise dir cannot be removed

RemoveDirectory(szFolderPath);
}

//void deldir(string dir)
JNIEXPORT void JNICALL Java_Demo_Drmng_deleteDirectory
  (JNIEnv *env, jobject obj, jstring dir)
{
    const char* dirchar = env->GetStringUTFChars(dir, 0);

   string nameOfDirectory(dirchar);
//  nameOfDirectory = path + nameOfDirectory;

  int n = nameOfDirectory.length(); 
  char char_array[n + 1]; 
  strcpy(char_array, nameOfDirectory.c_str());
   DeleteFolder(char_array);
}
//void listdir(string dir)
JNIEXPORT void JNICALL Java_Demo_Drmng_listDirectoryContents
  (JNIEnv *env, jobject obj, jstring dir)
{
    DIR *dp; //create the directory object
    struct dirent *entry; //create the entry structure
	const char* dirchar = env->GetStringUTFChars(dir, 0);
    dp=opendir(dirchar); //open directory by converting the string to const char*
    string direcPath(dirchar);
	if(direcPath.at(direcPath.length()-1)!='/'){
        direcPath=direcPath+"/";
    }
    if(dp!=NULL){ //if the directory isn't empty
         while( entry=readdir(dp) ){ //while there is something in the directory
             if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0){ //and if the entry isn't "." or ".."
                 if(isDir(direcPath + entry->d_name) == true){//check if the new path is a directory, and if it is (and recursion is specified as true), recurse.
                     cout<<entry->d_name<<endl;
					 string param = direcPath+entry->d_name;
					 
				//	 (env)->NewStringUTF(scene->getString.c_str());
                //     Java_Drmng_listDirectoryContents(env->NewStringUTF(param->getString.c_str())); //recurse
					 int n = param.length();
					 char char_array[n+1];
					 strcpy(char_array, param.c_str());
					 jstring jstr = (env)->NewStringUTF(char_array);
					Java_Demo_Drmng_listDirectoryContents(env, obj, jstr);
				 } else{
                     cout<<entry->d_name<<endl;
                      cout<<"dir = "<<direcPath<<endl;
                      string s = direcPath;
                      string s1 = string(entry->d_name);
                  //    s+=".txt";
                      s = s+s1;
                  //    cout<<s<<endl;
                  //    int status = remove(s.c_str());
                  //    cout<<status<<endl;
                  //    if(status!=0){
                  //        perror("following error has occurred");
                      }
                 } 
             }
         
      
         (void) closedir(dp); //close directory
    }
    else{
        perror ("Couldn't open the directory.");
    }
	env->ReleaseStringUTFChars(dir, dirchar);
}
//bool createFile(char fileName[])
JNIEXPORT jboolean JNICALL Java_Demo_Drmng_createFile
  (JNIEnv *env, jobject obj, jstring fileName)
{
	FILE* fptr;
	const char* fileNamechar = env->GetStringUTFChars(fileName, 0);
  string nameOfFile(fileNamechar);
//  nameOfFile = path + nameOfFile;

  int n = nameOfFile.length(); 
  char char_array[n + 1]; 
  strcpy(char_array, nameOfFile.c_str());


	fptr = fopen(char_array, "w");
	
	env->ReleaseStringUTFChars(fileName, fileNamechar);
  if(fptr ==NULL){
  //  printf("unable to create the file");
  //  exit(0);
    return false;
  }
  else{
  //  printf("enter the data to enter into the file\n");
  //  char data[1000];
  //  gets(data);
  //  fputs(data, fptr);
    fclose(fptr);
    return true;
  }
  return true;
}
//bool deleteFile(char fileName[])
JNIEXPORT jboolean JNICALL Java_Demo_Drmng_deleteFile
  (JNIEnv *env, jobject obj, jstring fileName)
{
	int status;
//	char* file_name;	
//	file_name = "del.txt";
	const char* fileNamechar = env->GetStringUTFChars(fileName, 0);
	
  string nameOfFile(fileNamechar);
 // nameOfFile = path + nameOfFile;

  int n = nameOfFile.length(); 
  char char_array[n + 1]; 
  strcpy(char_array, nameOfFile.c_str());
  status = remove(char_array);
	env->ReleaseStringUTFChars(fileName, fileNamechar);
  if(status==0){
  //  printf("file deleted successfully");
    return true;
  }
  else{
  //  printf("unable to delete file");
  //  perror("following error has occurred");
    return false;
  }
  return true;
}
JNIEXPORT void JNICALL Java_Demo_Drmng_appendDataToFile
  (JNIEnv *env, jobject obj, jstring fileName, jstring dataToAppend)
{
  FILE* fptr;
  const char* fileNamechar = env->GetStringUTFChars(fileName, 0);
  const char* dataToAppendchar = env->GetStringUTFChars(dataToAppend, 0);
  string data(dataToAppendchar);
  fptr = fopen(fileNamechar, "a");
  fputs(dataToAppendchar, fptr);
  fclose(fptr);
  env->ReleaseStringUTFChars(dataToAppend, dataToAppendchar);
  env->ReleaseStringUTFChars(fileName, fileNamechar);
}
void getdir(string dir, vector<string> &files, bool recursive){
    DIR *dp; //create the directory object
    struct dirent *entry; //create the entry structure
    dp=opendir(dir.c_str()); //open directory by converting the string to const char*
    if(dir.at(dir.length()-1)!='/'){
        dir=dir+"/";
    }
    if(dp!=NULL){ //if the directory isn't empty
         while( entry=(readdir(dp)) ){ //while there is something in the directory
             if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0){ //and if the entry isn't "." or ".."
                 if(isDir(dir + entry->d_name) == true && recursive == true){//check if the new path is a directory, and if it is (and recursion is specified as true), recurse.
                     files.push_back(string(entry->d_name)); //add entry to the list of files
                     getdir(dir + entry->d_name, files, true); //recurse
                 } else{
                      files.push_back(string(entry->d_name));//add the entry to the list of files
                      
                 } 
             }
         }
         (void) closedir(dp); //close directory
    }
    else{
        perror ("Couldn't open the directory.");
    }
}


JNIEXPORT jstring JNICALL Java_Demo_Drmng_getFile
  (JNIEnv *env, jobject obj, jstring fileName)
{
  const char* fileNamechar = env->GetStringUTFChars(fileName, 0);
  string fileNamestr(fileNamechar);
 // fileNamestr = path + fileNamestr;
  int n = fileNamestr.length(); 
  
    char char_array[n + 1]; 
   
    strcpy(char_array, fileNamestr.c_str()); 
  
    ifstream in(char_array);
  string contents;
  if(!in){
    cout<<"cannot open file\n";
    contents = "cannot open file\n";
  }
  char str[300];

  

  while(in){
    in.getline(str,300);
    string s(str);
    s += "\n";
    contents += s;
  }
//  cout<<contents;
  in.close();
      return (env)->NewStringUTF(contents.c_str());
 //   return contents;    
}

//void saveFile(string fname)
JNIEXPORT void JNICALL Java_Demo_Drmng_saveFile
  (JNIEnv *env, jobject obj, jstring fname, jstring contents)
{

  const char* fileNamechar = env->GetStringUTFChars(fname, 0);
  string fileNamestr(fileNamechar);
 // fileNamestr = path + fileNamestr;
  int n = fileNamestr.length(); 
  
    char char_array[n + 1]; 
   
    strcpy(char_array, fileNamestr.c_str());  
  ofstream myfile;
//  myfile.open("C:\\Users\\Administrator\\Desktop\\al\\testfile.txt");
  myfile.open(char_array);

  const char* contentsChar = env->GetStringUTFChars(contents, 0);
  string contentsStr(contentsChar);
  myfile << contentsStr;
  myfile.close();
  env->ReleaseStringUTFChars(fname, fileNamechar);
  env->ReleaseStringUTFChars(contents, contentsChar);
}

