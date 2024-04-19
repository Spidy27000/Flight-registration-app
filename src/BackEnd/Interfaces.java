package BackEnd;

interface IData{
}

interface ITable{
  // Creates table
  void Create();
  int Insert(IData data);
  void Update(int id, IData data);
  void Delete(int id);
}
