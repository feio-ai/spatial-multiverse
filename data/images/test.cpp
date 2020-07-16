#include <vector>
#include <filesystem>
#include <string> 
#include <iostream>
#include <fstream>

using std::string;

int main(int argc, char *argv[]) {
    string path = argv[1];
    std::vector<string> *img_list = new std::vector<string>();
    // std::vector<string> img_list;
    for (const auto & entry : std::filesystem::directory_iterator(path)) {
        string img_path = entry.path();
        img_list->push_back(img_path);
        std::cout << entry.path() << std::endl;
  }

}