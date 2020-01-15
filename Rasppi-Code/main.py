import networktables as nt
from networktables import NetworkTables
import threading
from networktables import NetworkTables

import calibration_data as cal
cond = threading.Condition()
ball_instruction_pipe = None
octa_instruction_pipe = None
notified = [None]
def connectionListener(connected, info):
    global cond
    global notified
    print(info, '; Connected=%s' % connected)
    with cond:
        notified[0] = True
        cond.notify()
def wrap_entry(table: nt.NetworkTable, name: str) -> nt.NetworkTableEntry:
    return table.getEntry(name)
def init_nettables_stuff():
    #init stuff
    global notified
    global ball_instruction_pipe
    global octa_instruction_pipe
    notified = [False]
    NetworkTables.initialize(server='10.80.58.2')
    NetworkTables.addConnectionListener(connectionListener, immediateNotify=True)
    with cond:
        print("Waiting")
    if not notified[0]:
        cond.wait()
    instance = nt.NetworkTablesInstance.getDefault()
    table = nt.NetworkTablesInstance.getTable(instance, "datatable")
    ball_instruction_pipe = wrap_entry(table, "image-processing-ball-pipeline")
    octa_instruction_pipe = wrap_entry(table, "image-processing-octa-pipeline")
import image_lib
while (1):
    #get ball

    for _ in range(1):
        out = image_lib.detectcircle(cal.BALL_CONSTANTS["HUE_MIN"],cal.BALL_CONSTANTS["SAT_MIN"],cal.BALL_CONSTANTS["VAL_MIN"],cal.BALL_CONSTANTS["HUE_MAX"],cal.BALL_CONSTANTS["SAT_MAX"],cal.BALL_CONSTANTS["VAL_MAX"],cal.BALL_CONSTANTS["DP"],cal.BALL_CONSTANTS["MIN_DIST"])
        if out[0].__class__ == None.__class__:
            break
        out[0] = out[0][0]
        if out[0].__class__ == None.__class__:
            break
        #test commit ilkcan
        if len(out[0]) != 1:
            break
        ball = max(out[0], key=(lambda i: i[2]))
        ball = out[0][0]
        ball_x = ball[0]
        ball_y = ball[1]
        x = ball_x / out[1][0]
        y = ball_y / out[1][1]
        print("BALL: ", [x, y])
    for _ in range(1):
        out = image_lib.detectocta(0,0,0,255,255,255)
        if out == None:
            break
        print("OCT ", out)

