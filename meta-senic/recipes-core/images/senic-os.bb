LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit core-image

IMAGE_BOOT_FILES = "zImage zImage-${KERNEL_DEVICETREE} boot.scr"

IMAGE_INSTALL = " \
	${@bb.utils.contains("DISTRO_FEATURES", "bluetooth", "bluez5-noinst-tools", "", d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "wifi", "linux-firmware-mediatek", "",d)} \	
	packagegroup-core-boot \
	packagegroup-base \	
	packagegroup-core-ssh-openssh \
	ifupdown \
	openssl \
	lighttpd \
	os-release \
	glibc-locale \
	glibc-utils \ 
	python \
	python-supervisor \
	python3 \
	python3-native \
	python3-pip \
	python3-dbus \
	python3-pygobject \
"
