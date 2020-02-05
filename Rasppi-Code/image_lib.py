import numpy as np
import sys
import time
from picamera.array import PiRGBArray
from picamera import PiCamera
import cv2
#1280x720
cam = PiCamera()
cam.resolution = (1280, 720)
cam.framerate = 20
capture = PiRGBArray(cam, size=(1280,720))
time.sleep(0.1)
def get_cam_frame():
    capture.truncate(0)
    cam.capture(capture, format="bgr")
    return capture.array
def _color_framing(frames,l_h,l_s,l_v,u_h,u_s,u_v):
    frame=frames
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    lower=np.array([l_h,l_s,l_v])
    upper=np.array([u_h,u_s,u_v])
    mask=cv2.inRange(hsv,lower,upper)
    frame=cv2.bitwise_and(frame,frame,mask=mask)
    kernel = np.ones((5,5), np.uint8) 
    frame=cv2.erode(frame, kernel, iterations=1)
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    blur = cv2.GaussianBlur(gray,(5,5),0)
    _, thresh_img = cv2.threshold(blur,91,255,cv2.THRESH_BINARY)
    return thresh_img

def detectcircle(l_h,l_s,l_v,u_h,u_s,u_v,d_p,m_d):
    frames = get_cam_frame()
    thresh_img=_color_framing(frames,l_h,l_s,l_v,u_h,u_s,u_v)
    h, w = thresh_img.shape
    circles = cv2.HoughCircles(thresh_img, cv2.HOUGH_GRADIENT, d_p, m_d)
    #each circle is (x, y, r)
    #return format is [[circle, circle, ...], [w, h]]
    return [circles, [w, h]]
def detectocta(l_h,l_s,l_v,u_h,u_s,u_v):
    frames = get_cam_frame()
    thresh_img = _color_framing(frames,l_h,l_s,l_v,u_h,u_s,u_v)
    h, w = thresh_img.shape
    conres = cv2.findContours(thresh_img, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    if not conres:
        return None
    contours = conres[1]
    if not contours:
        return None
    for cont in contours:
        area = cv2.contourArea(cont)
        if area > 400:
            approx = cv2.approxPolyDP(cont, 0.009 * cv2.arcLength(cont, True), True)
            if (len(approx) == 8):
                x = 0
                y = 0
                for point in approx:
                    x += point[0][0] / (w * 8)
                    y += point[0][1] / (h * 8)
                return [x,y]
def detecthexa(l_h,l_s,l_v,u_h,u_s,u_v):
    frames = get_cam_frame()
    frame = _color_framing(frames, l_h, l_s, l_v, u_h, u_s, u_v)
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    blur = cv2.GaussianBlur(gray,(5,5),0)
    _, thresh_img = cv2.threshold(blur,91,255,cv2.THRESH_BINARY)
    h, w = thresh_img.shape
    conres = cv2.findContours(thresh_img, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    if not conres:
        return None
    contours = conres[1]
    if not contours:
        return None
    for cont  in contours:
        area = cv2.contourArea(cont)
        if area > 400:
            approx = cv2.approxPolyDP(cont, 0.009 * cv2.arcLength(cont, True), True)
            if (len(approx) == 6):
                x = 0
                y = 0
                for point in approx:
                    x += point[0][0] / w
                    y += point[0][1] / h
                return [x,y]
