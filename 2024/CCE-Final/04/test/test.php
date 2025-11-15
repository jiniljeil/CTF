<?php

class AnyClass {
}

// create new Phar
$phar = new Phar('test.phar');
$phar->startBuffering();
$phar->addFromString('test.txt', 'text');
$phar->setStub("<?php __HALT_COMPILER(); ?>");

// add object of any class as meta data
$object = new AnyClass();
$phar->setMetadata($object);
$phar->stopBuffering();