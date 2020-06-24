#include "structs.hpp"
#include <stdint.h>
#include <sys/time.h>
#include <iostream>
#include <fstream>
#include <string> 
#include <sstream> 
#include <stdarg.h>
#include <signal.h>
#include <sys/wait.h>
#include <pwd.h>
#include <map>
#include <math.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <errno.h>
#include "FringeContext.h"
#include "functions.hpp"
#include "ArgAPI.hpp"
#include "Fixed.hpp"
#include <vector>
using std::vector;

#ifndef ZYNQ
typedef __int128 int128_t;
#endif

void printHelp(); 

using namespace std;

void Application(int numThreads, vector<string> * args) {
  // Create an execution context.
  FringeContext *c1 = new FringeContext("./verilog/accel.bit.bin");
  c1->load();
  vector<string>* x357 = args;
  int32_t x358 = 0;
  //int32_t* x359 = new int32_t {0}; // Initialize cpp argout ???
  int32_t x360 = 0;
  double x361 = 0;
  string x362;
  try {
    x362 = (*x357).at(0);
  }
  catch (std::out_of_range& e) {
    printHelp();
  }
  int32_t x363 = std::stol(x362);
  c1->setArg(ARGIN1_arg, x363, false);
  x358 = x363;
  c1->setArg(ARGIN2_arg, (int64_t)(7 * ((int64_t)1 << 16)), false); // x361
  x361 = 7;
  string x366;
  try {
    x366 = (*x357).at(1);
  }
  catch (std::out_of_range& e) {
    printHelp();
  }
  int32_t x367 = std::stol(x366);
  c1->setArg(IO1_arg, x367, true);
  x360 = x367;
  vector<int32_t>* x370 = new vector<int32_t>(64);
  for (int b12 = 0; b12 < 64; b12++) {
    int32_t x369 = b12 * 3;
    (*x370)[b12] = x369;
  }
  vector<int32_t>* x371 = new vector<int32_t>(1024);
  for (int b15 = 0; b15 < 1024; b15++) {
    (*x371)[b15] = b15;
  }
  vector<int32_t>* x376 = new vector<int32_t>(4096);
  for (int b17 = 0; b17 < 4096; b17++) {
    int32_t x372 = b17 >> 6;
    int32_t x373 = (int32_t) ((b17 % 64 + 64) % 64);
    int32_t x374 = x372 * 100;
    int32_t x375 = x374 + x373;
    (*x376)[b17] = x375;
  }
  vector<int32_t>* x378 = new vector<int32_t>(256);
  for (int b24 = 0; b24 < 256; b24++) {
    int32_t x377 = rand() % 5;
    (*x378)[b24] = x377;
  }
  uint64_t x379 = c1->malloc(sizeof(int32_t) * 64);
  c1->setArg(DRAM1D_ptr, x379, false);
  printf("Allocate mem of size 64 at %p\n", (void*)x379);
  uint64_t x380 = c1->malloc(sizeof(int32_t) * 1024);
  c1->setArg(DRAM1D_LONGER_ptr, x380, false);
  printf("Allocate mem of size 1024 at %p\n", (void*)x380);
  uint64_t x381 = c1->malloc(sizeof(int32_t) * 64*64);
  c1->setArg(DRAM2D_ptr, x381, false);
  printf("Allocate mem of size 64*64 at %p\n", (void*)x381);
  uint64_t x382 = c1->malloc(sizeof(int32_t) * 2*2*2*2*16);
  c1->setArg(DRAM5D_ptr, x382, false);
  printf("Allocate mem of size 2*2*2*2*16 at %p\n", (void*)x382);
  c1->memcpy(x379, &(*x370)[0], (*x370).size() * sizeof(int32_t));
  c1->memcpy(x380, &(*x371)[0], (*x371).size() * sizeof(int32_t));
  c1->memcpy(x381, &(*x376)[0], (*x376).size() * sizeof(int32_t));
  c1->memcpy(x382, &(*x378)[0], (*x378).size() * sizeof(int32_t));
  uint64_t x387 = c1->malloc(sizeof(int32_t) * 32*32);
  c1->setArg(DRAM_RESULT2D_ptr, x387, false);
  printf("Allocate mem of size 32*32 at %p\n", (void*)x387);
  uint64_t x388 = c1->malloc(sizeof(int32_t) * 1024);
  c1->setArg(DRAM_SCATTER1D_ptr, x388, false);
  printf("Allocate mem of size 1024 at %p\n", (void*)x388);
// Register ArgIns and ArgIOs in case some are unused
c1->setNumArgIns(2 + 6 + 1);
c1->setNumArgIOs(1);
c1->setNumArgOuts(1);
c1->setNumArgOutInstrs(0);
c1->setNumEarlyExits(0);
c1->flushCache(1024);
time_t tstart = time(0);
c1->run();
time_t tend = time(0);
double elapsed = difftime(tend, tstart);
std::cout << "Kernel done, test run time = " << elapsed << " ms" << std::endl;
c1->flushCache(1024);
vector<int32_t>* x842 = new vector<int32_t>(1024);
c1->memcpy(&(*x842)[0], x388, (*x842).size() * sizeof(int32_t));
vector<int32_t>* x844 = new vector<int32_t>(1024);
c1->memcpy(&(*x844)[0], x387, (*x844).size() * sizeof(int32_t));
string x846 = (string("Result 2D: ") + string("\n"));
std::cout << x846;
for (int b83 = 0; b83 < 32; b83 = b83 + 1) {
for (int b84 = 0; b84 < 32; b84 = b84 + 1) {
int32_t x848 = b83 << 5;
int32_t x849 = x848 + b84;
int32_t x850 = (*x844)[x849];
string x851 = std::to_string(x850);
string x852 = (x851 + string("\t"));
std::cout << x852;
}
std::cout << string("\n");
}
string x857 = (string("Result Scattered: ") + string("\n"));
std::cout << x857;
int32_t x859 = (*x842).size();
for (int b97 = 0; b97 < x859; b97 = b97 + 1) {
int32_t x860 = (*x842)[b97];
string x861 = std::to_string(x860);
string x862 = (x861 + string(" "));
std::cout << x862;
}
std::cout << string("\n");
vector<int32_t>* x871 = new vector<int32_t>(1024);
for (int b104 = 0; b104 < 1024; b104++) {
int32_t x866 = b104 >> 5;
int32_t x867 = (int32_t) ((b104 % 32 + 32) % 32);
int32_t x868 = 32 + x866;
int32_t x869 = x868 * 100;
int32_t x870 = x869 + x867;
(*x871)[b104] = x870;
}
vector<bool>* x875 = new vector<bool>((*x871).size());
for (int b112 = 0; b112 < (*x871).size(); b112++) { 
int32_t x872 = (*x871)[b112];
int32_t x873 = (*x844)[b112];
bool x874 = x872 == x873;
(*x875)[b112] = x874;
}
bool x878;
if ((*x875).size() > 0) { // Hack to handle reductions on things of length 0
x878 = (*x875)[0];
}
else {
x878 = 0;
}
for (int b118 = 1; b118 < (*x875).size(); b118++) {
bool b119 = (*x875)[b118];
bool b120 = x878;
bool x877 = b119 & b120;
x878 = x877;
}
vector<bool>* x882 = new vector<bool>(64);
for (int b124 = 0; b124 < 64; b124++) {
int32_t x879 = 3 * b124;
int32_t x880 = (*x842)[x879];
bool x881 = x880 == x879;
(*x882)[b124] = x881;
}
bool x885;
if ((*x882).size() > 0) { // Hack to handle reductions on things of length 0
x885 = (*x882)[0];
}
else {
x885 = 0;
}
for (int b129 = 1; b129 < (*x882).size(); b129++) {
bool b130 = (*x882)[b129];
bool b131 = x885;
bool x884 = b130 & b131;
x885 = x884;
}
string x886 = x878 ? string("true") : string("false");
string x887 = (string("2D pass? ") + x886);
string x888 = (x887 + string("\n"));
std::cout << x888;
string x890 = x885 ? string("true") : string("false");
string x891 = (string("scatter pass? ") + x890);
string x892 = (x891 + string("\n"));
std::cout << x892;
int64_t x894_tmp = c1->getArg(ARGOUT1_arg, false);
bool x894_sgned = true & ((x894_tmp & ((int64_t)1 << 31)) > 0); // Determine sign
if (x894_sgned) x894_tmp = x894_tmp | ~(((int64_t)1 << 32)-1); // Sign-extend if necessary
int32_t x894 = (int32_t) x894_tmp / ((int64_t)1 << 0);
int64_t x895_tmp = c1->getArg(IO1_arg, true);
bool x895_sgned = true & ((x895_tmp & ((int64_t)1 << 31)) > 0); // Determine sign
if (x895_sgned) x895_tmp = x895_tmp | ~(((int64_t)1 << 32)-1); // Sign-extend if necessary
int32_t x895 = (int32_t) x895_tmp / ((int64_t)1 << 0);
string x896 = std::to_string(x894);
string x897 = (string("Received ") + x896);
string x898 = (x897 + string(" and "));
string x899 = std::to_string(x895);
string x900 = (x898 + x899);
string x901 = (x900 + string("\n"));
std::cout << x901;
int32_t x903 = x363 + x367;
bool x904 = x894 == x903;
bool x905 = x895 == x363;
bool x906 = x904 & x905;
string x907 = x906 ? string("true") : string("false");
string x908 = (string("ArgTest pass? ") + x907);
string x909 = (x908 + string("\n"));
std::cout << x909;
delete c1;
}

void printHelp() {
fprintf(stderr, "Help for app: HelloSpatial\n");
fprintf(stderr, "  -- Args:    <0: cksum> <1: cksum>\n");
exit(1);
}

int main(int argc, char *argv[]) {
vector<string> *args = new vector<string>(argc-1);
for (int i=1; i<argc; i++) {
(*args)[i-1] = std::string(argv[i]);
if (std::string(argv[i]) == "--help" | std::string(argv[i]) == "-h") {printHelp();}
}
int numThreads = 1;
char *env_threads = getenv("DELITE_NUM_THREADS");
if (env_threads != NULL) { numThreads = atoi(env_threads); } else {
  fprintf(stderr, "[WARNING]: DELITE_NUM_THREADS undefined, defaulting to 1\n");
}
fprintf(stderr, "Executing with %d thread(s)\n", numThreads);
Application(numThreads, args);
return 0;
}
