import networktables as nt
from networktables import NetworkTables
NetworkTables.initialize(server='roboRIO-8058-FRC.local')
instance = NetworkTables.getTable("datatables")