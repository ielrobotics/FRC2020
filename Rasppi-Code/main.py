import networktables as nt
from networktables import NetworkTables
import threading
from networktables import NetworkTables

cond = threading.Condition()
notified = [False]

def connectionListener(connected, info):
    global cond
    print(info, '; Connected=%s' % connected)
    with cond:
        notified[0] = True
        cond.notify()

NetworkTables.initialize(server='10.80.58.2')
NetworkTables.addConnectionListener(connectionListener, immediateNotify=True)

with cond:
    print("Waiting")
    if not notified[0]:
        cond.wait()

def wrap_entry(table: nt.NetworkTable, name: str) -> nt.NetworkTableEntry:
    return table.getEntry(name)
instance = nt.NetworkTablesInstance.getDefault()
table = nt.NetworkTablesInstance.getTable("datatable")
command_pipe = wrap_entry(table, "image-processing-commands")