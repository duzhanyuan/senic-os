senicOS -- The Senic Linux distribution
=======================================

Well, first of all, admittedly "senicOS" is a bit of a mouthful - it's not actually a custom operating system, but simply a `yocto <https://www.yoctoproject.org/>`_ based **linux distribution**.

You can `find the sources on github <https://github.com/getsenic/senic-os>`_.


Building senicOS on a remote host
---------------------------------

The ``Makefile`` supports both running locally on a suitable Linux machine that is able to build the OS as well as running the checkout on a non-Linux machine (typically macos or FreeBSD) and control the actual building on a remote host.

To this end the ``Makefile`` is currently hard coded to perform these actions on and against a host named ``osbuild``. It then falls into the responsibility of the user to provide a suitable SSH configuration for such host.

To update the host with local (uncommitted changes) run ``make upload``.

You can then perform a build with these settings on the host like so::

    source oe/oe-init-build-env 

This will use (and create) the default build directory named ``build``, however, you can provide an arbitrary custom name (usually 'your' username) i.e.::

    source oe/oe-init-build-env xxx

and then perform a separate build in your own environment.

To download the build, run ``make download`` on your local instance, this will (r)sync the server's build directory to the local build directory (in essence as if you had run the build command locally) at ``build/tmp-glibc/deploy/images/senic-hub-beta/``.


Notes on controlling the build host from FreeBSD
------------------------------------------------

Writing the wic to a USB card reader
************************************

First, make sure to get the the device name of the card reader (usually ``da0``) by running ``camcontrol devlist``.

Once downloaded use ``dd`` but make sure to provide a sufficiently large blocksize, i.e. 1024k::

    sudo dd if=build/tmp-glibc/deploy/images/senic-hub-beta/senic-os-dev-senic-hub-beta.wic of=/dev/da0 bs=1024k


Connecting to the serial console
********************************

Check the device name of the serial adapter by performing a diff between the contents of ``ls -a /dev/`` before and after plugging in the adapter (usually ``ttyU0``), then use screen (either as root or with sudo!)::

    sudo screen /dev/ttyU0 115200 -L

