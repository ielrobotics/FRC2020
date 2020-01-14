import networktables as nt
from networktables import NetworkTables
import threading
from networktables import NetworkTables
#init stuff
#cond = threading.Condition()
#notified = [False]

#def connectionListener(connected, info):
#    global cond
#    print(info, '; Connected=%s' % connected)
#    with cond:
#        notified[0] = True
#        cond.notify()

#NetworkTables.initialize(server='10.80.58.2')
#NetworkTables.addConnectionListener(connectionListener, immediateNotify=True)

#with cond:
#    print("Waiting")
#    if not notified[0]:
#        cond.wait()

#def wrap_entry(table: nt.NetworkTable, name: str) -> nt.NetworkTableEntry:
#    return table.getEntry(name)
#instance = nt.NetworkTablesInstance.getDefault()
#table = nt.NetworkTablesInstance.getTable("datatable")
#command_pipe = wrap_entry(table, "image-processing-commands")

import image_lib
while (1):
    out = image_lib.detectcircle(13,0,0,39,255,255,3,160)
    if out[0].__class__ == None.__class__:
        continue
    out[0] = out[0][0]
    if out[0].__class__ == None.__class__:
        continue
    if out[0].__class__ == None.__class__:
        continue
    if len(out[0]) != 1:
        continue
    ball = out[0][0]
    ball_x = ball[0]
    ball_y = ball[1]
    x = ball_x / out[1][0]
    y = ball_y / out[1][1]
    print([x, y])