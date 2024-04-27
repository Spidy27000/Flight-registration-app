package BackEnd;

enum Table {
  PASSENGER(0),
  LOGIN(1),
  PLANE(2),
  FLIGHT(3),
  PAYMENT(4),
  BOOKING(5);

  final int val;

  Table(int value) {
    this.val = value;
  }
}

abstract class IData {
  int id;
}

interface ITable {
  // Creates table
  void Create();

  void Insert(IData data);

  void Update(int id, IData data);

  void Delete(int id);
}
